package com.okconde.bestepstyle.feature.productmangement.service;

import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamRequest;
import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamSearchRequest;
import com.okconde.bestepstyle.core.dto.sanpham.response.SanPhamResponse;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTRequest;
import com.okconde.bestepstyle.core.entity.SanPham;
import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.sanpham.request.SanPhamRequestMapper;
import com.okconde.bestepstyle.core.mapper.sanpham.response.SanPhamResponseMapper;
import com.okconde.bestepstyle.core.repository.SanPhamChiTietRepository;
import com.okconde.bestepstyle.core.repository.SanPhamRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusSP;
import com.okconde.bestepstyle.core.util.enumutil.StatusSPCT;
import com.okconde.bestepstyle.core.util.formater.DateFormater;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    //Constructor
    public SanPhamService(SanPhamRepository sanPhamRepository, SanPhamChiTietRepository sanPhamChiTietRepository,
                          SanPhamResponseMapper sanPhamResponseMapper, SanPhamRequestMapper sanPhamRequestMapper) {
        this.sanPhamRepository = sanPhamRepository;
        this.sanPhamChiTietRepository = sanPhamChiTietRepository;
        this.sanPhamResponseMapper = sanPhamResponseMapper;
        this.sanPhamRequestMapper = sanPhamRequestMapper;
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
    public SanPhamResponse create(SanPhamRequest sanPhamRequest) {
        // Tạo mã sản phẩm cho sản phẩm cha
        sanPhamRequest.setMaSanPham(GenerateCodeRandomUtil.generateProductCode("SP", 8));



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
        for (SanPhamChiTiet sanPhamChiTiet : sanPham.getSanPhamChiTiets()) {
            // Tạo mã cho sản phẩm con
            sanPhamChiTiet.setMaSpct(GenerateCodeRandomUtil.generateProductCode("SPCT", 6));
            sanPhamChiTiet.setSanPham(sanPhamSaved); // Liên kết sản phẩm con với sản phẩm cha
            sanPhamChiTiet.setTrangThai(StatusSPCT.ACTIVE);
            sanPhamChiTiet.setNgayTao(LocalDateTime.now());
            sanPhamChiTiet.setNgayChinhSua(LocalDateTime.now());
        }
        sanPhamChiTietRepository.saveAll(sanPham.getSanPhamChiTiets());

        // Chuyển sanPhamSaved thành SanPhamResponse DTO
        return sanPhamResponseMapper.toDTO(sanPhamSaved);
    }




    @Override
    public SanPhamResponse update(Long aLong, SanPhamRequest sanPhamRequest) {
        return null;
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

