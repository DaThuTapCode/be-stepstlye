package com.okconde.bestepstyle.feature.productmangement.service;

import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamRequest;
import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamSearchRequest;
import com.okconde.bestepstyle.core.dto.sanpham.response.SanPhamResponse;
import com.okconde.bestepstyle.core.entity.SanPham;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.sanpham.request.SanPhamRequestMapper;
import com.okconde.bestepstyle.core.mapper.sanpham.response.SanPhamResponseMapper;
import com.okconde.bestepstyle.core.repository.SanPhamRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusSP;
import com.okconde.bestepstyle.core.util.formater.DateFormater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    //Mapper
    private final SanPhamResponseMapper sanPhamResponseMapper;
    private final SanPhamRequestMapper sanPhamRequestMapper;
    //Constructor
    public SanPhamService(SanPhamRepository sanPhamRepository,
                          SanPhamResponseMapper sanPhamResponseMapper, SanPhamRequestMapper sanPhamRequestMapper) {
        this.sanPhamRepository = sanPhamRepository;
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
    public SanPhamResponse create(SanPhamRequest sanPhamRequest) {
        sanPhamRequest.setMaSanPham(GenerateCodeRandomUtil.generateProductCode("SP", 8));
        sanPhamRequest.setTrangThai(StatusSP.ACTIVE);
        return sanPhamResponseMapper.toDTO(sanPhamRepository.save(sanPhamRequestMapper.toEntity(sanPhamRequest)));
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
        Page<SanPham> sanPhamPage = sanPhamRepository.searchPage(pageable,
                sanPhamSearchRequest.getTenSanPham(),
                sanPhamSearchRequest.getNgayTaoStart(),
                DateFormater.setEndDate(sanPhamSearchRequest.getNgayTaoEnd()),
                sanPhamSearchRequest.getIdDanhMuc(),
                sanPhamSearchRequest.getIdThuongHieu());
        return sanPhamPage.map(sanPhamResponseMapper::toDTO);
    }
}

