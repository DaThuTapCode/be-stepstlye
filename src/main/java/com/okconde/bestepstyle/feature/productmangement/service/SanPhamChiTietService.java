package com.okconde.bestepstyle.feature.productmangement.service;

import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTRequest;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;
import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.sanphamchitiet.response.SPCTResponseMapper;
import com.okconde.bestepstyle.core.repository.SanPhamChiTietRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    // Mapper
    private final SPCTResponseMapper spctResponseMapper;

    // Constructor
    public SanPhamChiTietService(SanPhamChiTietRepository sanPhamChiTietRepository, SPCTResponseMapper spctResponseMapper) {
        this.sanPhamChiTietRepository = sanPhamChiTietRepository;
        this.spctResponseMapper = spctResponseMapper;
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
    public SPCTResponse getById(Long id) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm chi tiết với id: " + id));
        return spctResponseMapper.toDTO(sanPhamChiTiet);
    }
}
