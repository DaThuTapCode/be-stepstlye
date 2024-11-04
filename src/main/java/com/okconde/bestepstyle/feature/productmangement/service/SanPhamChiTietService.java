package com.okconde.bestepstyle.feature.productmangement.service;

import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTRequest;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;
import com.okconde.bestepstyle.core.entity.SanPham;
import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.sanphamchitiet.request.SPCTRequestMapper;
import com.okconde.bestepstyle.core.mapper.sanphamchitiet.response.SPCTResponseMapper;
import com.okconde.bestepstyle.core.repository.SanPhamChiTietRepository;
import com.okconde.bestepstyle.core.repository.SanPhamRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 20:26
 *
 * @author Trong Phu
 */
@Service(value = "SanPhamChiTietService")
public class SanPhamChiTietService implements IBaseService<SanPhamChiTiet, Long, SPCTRequest, SPCTResponse> {
    // Repo
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    private final SanPhamRepository sanPhamRepository;

    // Mapper
    private final SPCTResponseMapper spctResponseMapper;
    private final SPCTRequestMapper spctRequestMapper;

    // Constructor
    public SanPhamChiTietService(SanPhamChiTietRepository sanPhamChiTietRepository, SanPhamRepository sanPhamRepository, SPCTResponseMapper spctResponseMapper, SPCTRequestMapper spctRequestMapper) {
        this.sanPhamChiTietRepository = sanPhamChiTietRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.spctResponseMapper = spctResponseMapper;
        this.spctRequestMapper = spctRequestMapper;
    }


    @Override
    public List<SPCTResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<SPCTResponse> getAll() {
        return spctResponseMapper.listToDTO(sanPhamChiTietRepository.findAll());
    }

    @Override
    public SPCTResponse create(SPCTRequest spctRequest) {
        return null;
    }

    @Override
    public SPCTResponse update(Long aLong, SPCTRequest spctRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    @Transactional
    public SPCTResponse getById(Long id) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm chi tiết với id: " + id));
        return spctResponseMapper.toDTO(sanPhamChiTiet);
    }

    public void createListSPCTByIDSP(Long idSanPham, List<SPCTRequest> spctRequest) {
        // Check sự tồn tại của sản phẩm
        SanPham sanPhamExisting = sanPhamRepository.findById(idSanPham).orElseThrow(
                () -> new BusinessException("Sản phẩm không tồn tại")
        );

        //Set các tham số cần thiết cho danh sách spct mới
        List<SanPhamChiTiet> listSPCTNew = spctRequestMapper.toListEntity(spctRequest);
        listSPCTNew.forEach(spctRequest1 -> {
            spctRequest1.setSanPham(sanPhamExisting);
            spctRequest1.setNgayTao(LocalDateTime.now());
            spctRequest1.setMaSpct(GenerateCodeRandomUtil.generateProductCode("SPCT", 6));
        });
        //Truy vấn kiêm tra tồn tại trước khi thêm
        listSPCTNew.forEach(spctRequest1 -> {
            if(sanPhamChiTietRepository.checkExitsByAttribute(sanPhamExisting.getIdSanPham(), spctRequest1.getChatLieu().getIdChatLieu(),
                    spctRequest1.getChatLieuDeGiay().getIdChatLieuDeGiay(),
                    spctRequest1.getKichCo().getIdKichCo(),
                    spctRequest1.getMauSac().getIdMauSac(),
                    spctRequest1.getKieuDeGiay().getIdKieuDeGiay(),
                    spctRequest1.getTrongLuong().getIdTrongLuong())){
                String errorMessage = String.format(
                        "Sản phẩm chi tiết với các thuộc tính sau đã tồn tại: " +
                                "Chất liệu: %s, " +
                                "Chất liệu đế giày: %s, " +
                                "Kích cỡ: %s, " +
                                "Màu sắc: %s, " +
                                "Kiểu đế giày: %s, " +
                                "Trọng lượng: %s.",
                        spctRequest1.getChatLieu().getTenChatLieu(),
                        spctRequest1.getChatLieuDeGiay().getTenChatLieuDeGiay(),
                        spctRequest1.getKichCo().getGiaTri(),
                        spctRequest1.getMauSac().getTenMau(),
                        spctRequest1.getKieuDeGiay().getTenKieuDeGiay(),
                        spctRequest1.getTrongLuong().getGiaTri()
                );

                throw new BusinessException(errorMessage);
            }
        });

        // Lưu
        List<SanPhamChiTiet> listSPCTsByIdSanPham = sanPhamChiTietRepository.getSPCTByIdSP(sanPhamExisting.getIdSanPham());

        sanPhamChiTietRepository.saveAll(listSPCTNew);
    }
}
