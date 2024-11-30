package com.okconde.bestepstyle.feature.productmangement.service;

import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamRequest;
import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamSearchRequest;
import com.okconde.bestepstyle.core.dto.sanpham.response.SanPhamResponse;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTRequest;
import com.okconde.bestepstyle.core.entity.*;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.sanpham.request.SanPhamRequestMapper;
import com.okconde.bestepstyle.core.mapper.sanpham.response.SanPhamResponseMapper;
import com.okconde.bestepstyle.core.repository.*;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusSP;
import com.okconde.bestepstyle.core.util.enumutil.StatusSPCT;
import com.okconde.bestepstyle.core.util.file.FileUpLoadUtil;
import com.okconde.bestepstyle.core.util.formater.DateFormater;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 20:26
 *
 * @author Trong Phu
 */
@Service(value = "SanPhamService")
public class SanPhamService implements IBaseService<SanPham, Long, SanPhamRequest, SanPhamResponse> {
    //Repo
    private final SanPhamRepository sanPhamRepository;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;

    //Mapper
    private final SanPhamResponseMapper sanPhamResponseMapper;
    private final SanPhamRequestMapper sanPhamRequestMapper;
    private final MauSacRepository mauSacRepository;
    private final KichCoRepository kichCoRepository;
    private final ThuongHieuRepository thuongHieuRepository;
    private final DanhMucRepository danhMucRepository;
    private final ChatLieuRepository chatLieuRepository;
    private final TrongLuongRepository trongLuongRepository;

    //Constructor
    public SanPhamService(SanPhamRepository sanPhamRepository, SanPhamChiTietRepository sanPhamChiTietRepository,
                          SanPhamResponseMapper sanPhamResponseMapper, SanPhamRequestMapper sanPhamRequestMapper, MauSacRepository mauSacRepository, KichCoRepository kichCoRepository, ThuongHieuRepository thuongHieuRepository, DanhMucRepository danhMucRepository, ChatLieuRepository chatLieuRepository, TrongLuongRepository trongLuongRepository) {
        this.sanPhamRepository = sanPhamRepository;
        this.sanPhamChiTietRepository = sanPhamChiTietRepository;
        this.sanPhamResponseMapper = sanPhamResponseMapper;
        this.sanPhamRequestMapper = sanPhamRequestMapper;
        this.mauSacRepository = mauSacRepository;
        this.kichCoRepository = kichCoRepository;
        this.thuongHieuRepository = thuongHieuRepository;
        this.danhMucRepository = danhMucRepository;
        this.chatLieuRepository = chatLieuRepository;
        this.trongLuongRepository = trongLuongRepository;
    }


    @Override
    public List<SanPhamResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<SanPhamResponse> getAll() {
        return sanPhamResponseMapper.listToDTO(sanPhamRepository.findAll());
    }

    @Override
    @Transactional
    public SanPhamResponse create(SanPhamRequest sanPhamRequest) throws Exception {
        // Tạo mã sản phẩm cho sản phẩm cha
        sanPhamRequest.setMaSanPham(GenerateCodeRandomUtil.generateProductCode("SP", 8));

        ThuongHieu thuongHieu = thuongHieuRepository.findById(sanPhamRequest.getThuongHieu().getIdThuongHieu())
                .orElseThrow(() -> new BusinessException("Thương hiệu không tồn tại"));

        DanhMuc danhMuc = danhMucRepository.findById(sanPhamRequest.getDanhMuc().getIdDanhMuc())
                .orElseThrow(() -> new BusinessException("Danh mục không tồn tại"));

        ChatLieu chatLieu = chatLieuRepository.findById(sanPhamRequest.getChatLieu().getIdChatLieu())
                .orElseThrow(() -> new BusinessException("Chất liệu không tồn tại"));

        TrongLuong trongLuong = trongLuongRepository.findById(sanPhamRequest.getTrongLuong().getIdTrongLuong())
                .orElseThrow(() -> new BusinessException("Trọng lượng không tồn tại"));
        // Kiểm tra trùng mã sản phẩm
        if (sanPhamRepository.getSanPhamByMaSanPham(sanPhamRequest.getMaSanPham()).isPresent()) {
            throw new BusinessException("Mã sản phẩm " + sanPhamRequest.getMaSanPham() + " đã tồn tại");
        }

        // Kiểm tra trùng tên sản phẩm
        if (sanPhamRepository.getSanPhamByTenSanPham(sanPhamRequest.getTenSanPham().trim()).isPresent()) {
            throw new BusinessException("Tên sản phẩm " + sanPhamRequest.getTenSanPham() + " đã tồn tại");
        }

        // Thiết lập trạng thái cho sản phẩm cha
        sanPhamRequest.setTrangThai(StatusSP.ACTIVE);

        // Chuyển SanPhamRequest thành SanPham entity
        SanPham sanPham = sanPhamRequestMapper.toEntity(sanPhamRequest);

        // Lưu sản phẩm cha (bao gồm cả sản phẩm con nếu cascade đã được thiết lập)
        SanPham sanPhamSaved = sanPhamRepository.save(sanPham);

        // Liên kết sản phẩm con với sản phẩm cha
        List<SanPhamChiTiet> sanPhamChiTietList = new ArrayList<>();
        for (SPCTRequest sanPhamChiTiet : sanPhamRequest.getSanPhamChiTiets()) {
            // Tạo mã cho sản phẩm con
            MauSac mauSac = mauSacRepository.findById(sanPhamChiTiet.getMauSac().getIdMauSac()).orElseThrow(
                    () -> new BusinessException("Màu sắc không tồn tại")
            );
            KichCo kichCo = kichCoRepository.findById(sanPhamChiTiet.getKichCo().getIdKichCo()).orElseThrow(
                    () -> new BusinessException("Kích cỡ không tồn tại")
            );
            SanPhamChiTiet sanPhamChiTietNew = new SanPhamChiTiet();
            sanPhamChiTietNew.setMaSpct(GenerateCodeRandomUtil.generateProductCode("SPCT", 6));
            sanPhamChiTietNew.setMauSac(mauSac);
            sanPhamChiTietNew.setKichCo(kichCo);
            sanPhamChiTietNew.setGia(sanPhamChiTiet.getGia());
            sanPhamChiTietNew.setSoLuong(sanPhamChiTiet.getSoLuong());
            sanPhamChiTietNew.setSanPham(sanPhamSaved); // Liên kết sản phẩm con với sản phẩm cha
            sanPhamChiTietNew.setTrangThai(StatusSPCT.ACTIVE);
            sanPhamChiTietNew.setNgayTao(LocalDateTime.now());
            sanPhamChiTietNew.setNgayChinhSua(LocalDateTime.now());
            sanPhamChiTietNew.setAnh(FileUpLoadUtil.uploadFile(sanPhamChiTiet.getAnhFile(), FileUpLoadUtil.FILE_TYPE_IMAGE, 2L));
            sanPhamChiTietList.add(sanPhamChiTietNew);
        }
        sanPhamChiTietRepository.saveAll(sanPhamChiTietList);

        // Chuyển sanPhamSaved thành SanPhamResponse DTO
        return sanPhamResponseMapper.toDTO(sanPhamSaved);
    }




    @Override
    @Transactional
    public SanPhamResponse update(Long idSanPham, SanPhamRequest sanPhamRequest) {
        SanPham sanPham = sanPhamRepository.findById(idSanPham).orElseThrow(
                () -> new BusinessException("Sản phẩm không tồn tại")
        );
        ThuongHieu thuongHieu = thuongHieuRepository.findById(sanPhamRequest.getThuongHieu().getIdThuongHieu())
                .orElseThrow(() -> new BusinessException("Thương hiệu không tồn tại"));

        DanhMuc danhMuc = danhMucRepository.findById(sanPhamRequest.getDanhMuc().getIdDanhMuc())
                .orElseThrow(() -> new BusinessException("Danh mục không tồn tại"));

        ChatLieu chatLieu = chatLieuRepository.findById(sanPhamRequest.getChatLieu().getIdChatLieu())
                .orElseThrow(() -> new BusinessException("Chất liệu không tồn tại"));

        TrongLuong trongLuong = trongLuongRepository.findById(sanPhamRequest.getTrongLuong().getIdTrongLuong())
                .orElseThrow(() -> new BusinessException("Trọng lượng không tồn tại"));

        sanPham.setChatLieu(chatLieu);
        sanPham.setTrongLuong(trongLuong);
        sanPham.setDanhMuc(danhMuc);
        sanPham.setThuongHieu(thuongHieu);
        sanPham.setNgayChinhSua(LocalDateTime.now());
        sanPham.setTenSanPham(sanPhamRequest.getTenSanPham());
        sanPham.setMoTa(sanPhamRequest.getMoTa());

        return sanPhamResponseMapper.toDTO(sanPham);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public SanPhamResponse getById(Long aLong) {
        SanPham sanPham = sanPhamRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm với id: " + aLong));
        return sanPhamResponseMapper.toDTO(sanPham);
    }

    /**
     * Hàm tìm kiếm kết hợp phân trang sản phẩm
     * */
    public Page<SanPhamResponse> searchPageProduct(
            Pageable pageable,
            SanPhamSearchRequest sanPhamSearchRequest
    ){
        Page<SanPham> sanPhamPage = sanPhamRepository.searchPageSanPham(pageable,
                sanPhamSearchRequest.getMaSanPham(),
                sanPhamSearchRequest.getTenSanPham(),
                sanPhamSearchRequest.getNgayTaoStart(),
                DateFormater.setEndDate(sanPhamSearchRequest.getNgayTaoEnd()),
                sanPhamSearchRequest.getIdDanhMuc(),
                sanPhamSearchRequest.getIdThuongHieu());
        return sanPhamPage.map(sanPhamResponseMapper::toDTO);
    }
}

