package com.okconde.bestepstyle.feature.employeemanagement.service;

import com.okconde.bestepstyle.core.dto.chucvu.request.ChucVuRequest;
import com.okconde.bestepstyle.core.dto.chucvu.response.ChucVuResponse;
import com.okconde.bestepstyle.core.entity.ChucVu;
import com.okconde.bestepstyle.core.mapper.chucvu.response.ChucVuResponseMapper;
import com.okconde.bestepstyle.core.repository.ChucVuRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Quang Minh on 9/25/2024 20:26:58
 *
 * @author Quang Minh
 */
@Service
public class ChucVuService implements IBaseService<ChucVu, Long, ChucVuRequest, ChucVuResponse> {

    private final ChucVuRepository chucVuRepository;

    private final ChucVuResponseMapper chucVuResponseMapper;

    public ChucVuService(
            ChucVuRepository chucVuRepository,
            ChucVuResponseMapper chucVuResponseMapper) {
        this.chucVuRepository = chucVuRepository;

        this.chucVuResponseMapper = chucVuResponseMapper;
    }


    @Override
    public List<ChucVuResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<ChucVuResponse> getAll() {

        List<ChucVu> chucVuList = chucVuRepository.findAll();
        return chucVuResponseMapper.listToDTO(chucVuList);
    }

    @Override
    public ChucVuResponse create(ChucVuRequest chucVuRequest) {
        return null;
    }

    @Override
    public ChucVuResponse update(Long aLong, ChucVuRequest chucVuRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public ChucVuResponse getById(Long aLong) {
        return null;
    }
}
