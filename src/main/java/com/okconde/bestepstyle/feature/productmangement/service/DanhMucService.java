package com.okconde.bestepstyle.feature.productmangement.service;

import com.okconde.bestepstyle.core.dto.danhmuc.request.DanhMucRequest;
import com.okconde.bestepstyle.core.dto.danhmuc.response.DanhMucResponse;
import com.okconde.bestepstyle.core.entity.DanhMuc;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.danhmuc.response.DanhMucResponseMapper;
import com.okconde.bestepstyle.core.repository.DanhMucRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 20:26
 *
 * @author Trong Phu
 */
@Service(value = "DanhMucService")
public class DanhMucService implements IBaseService <DanhMuc, Long, DanhMucRequest, DanhMucResponse>{

    private final DanhMucRepository danhMucRepository;

    private final DanhMucResponseMapper danhMucResponseMapper;

    public DanhMucService(
            DanhMucRepository danhMucRepository,
            DanhMucResponseMapper danhMucResponseMapper
    ) {
        this.danhMucRepository = danhMucRepository;
        this.danhMucResponseMapper = danhMucResponseMapper;
    }


    @Override
    public List<DanhMucResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<DanhMucResponse> getAll() {
        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        return danhMucResponseMapper.listToDTO(danhMucList);
    }

    @Override
    public DanhMucResponse create(DanhMucRequest danhMucRequest) {
        return null;
    }

    @Override
    public DanhMucResponse update(Long aLong, DanhMucRequest danhMucRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public DanhMucResponse getById(Long aLong) {
        DanhMuc danhMuc = danhMucRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục với id: " +aLong));
        return danhMucResponseMapper.toDTO(danhMuc);
    }
}
