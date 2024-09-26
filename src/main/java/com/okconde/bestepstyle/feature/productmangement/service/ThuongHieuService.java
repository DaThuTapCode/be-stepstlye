package com.okconde.bestepstyle.feature.productmangement.service;

import com.okconde.bestepstyle.core.dto.thuonghieu.request.ThuongHieuRequest;
import com.okconde.bestepstyle.core.dto.thuonghieu.response.ThuongHieuResponse;
import com.okconde.bestepstyle.core.entity.ThuongHieu;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.thuonghieu.response.ThuongHieuResponseMapper;
import com.okconde.bestepstyle.core.repository.ThuongHieuRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 20:27
 *
 * @author Trong Phu
 */
@Service(value = "ThuongHieuService")
public class ThuongHieuService implements IBaseService<ThuongHieu, Long, ThuongHieuRequest, ThuongHieuResponse> {

    //Repo
    private final ThuongHieuRepository thuongHieuRepository;

    //mapper
    private final ThuongHieuResponseMapper thuongHieuResponseMapper;

    //Constructor
    public ThuongHieuService(ThuongHieuRepository thuongHieuRepository, ThuongHieuResponseMapper thuongHieuResponseMapper) {
        this.thuongHieuRepository = thuongHieuRepository;
        this.thuongHieuResponseMapper = thuongHieuResponseMapper;
    }


    @Override
    public List<ThuongHieuResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<ThuongHieuResponse> getAll() {
        return thuongHieuResponseMapper.listToDTO(thuongHieuRepository.findAll());
    }

    @Override
    public ThuongHieuResponse create(ThuongHieuRequest thuongHieuRequest) {
        return null;
    }

    @Override
    public ThuongHieuResponse update(Long aLong, ThuongHieuRequest thuongHieuRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public ThuongHieuResponse getById(Long aLong) {
        ThuongHieu thuongHieu = thuongHieuRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thương hiệu với id: " + aLong));
        return thuongHieuResponseMapper.toDTO(thuongHieu);
    }
}
