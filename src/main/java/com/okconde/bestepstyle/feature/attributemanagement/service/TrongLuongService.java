package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
import com.okconde.bestepstyle.core.dto.trongluong.request.TrongLuongRequest;
import com.okconde.bestepstyle.core.entity.TrongLuong;
import com.okconde.bestepstyle.core.mapper.trongluong.response.TrongLuongResponseMapper;
import com.okconde.bestepstyle.core.repository.TrongLuongRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Service
public class TrongLuongService implements IBaseService<TrongLuong, Long, TrongLuongRequest, TrongLuongResponse> {

    private final TrongLuongRepository trongLuongRepository;

    private final TrongLuongResponseMapper trongLuongResponseMapper;

    public TrongLuongService(
            TrongLuongRepository trongLuongRepository,
            TrongLuongResponseMapper trongLuongResponseMapper
    ) {
        this.trongLuongRepository = trongLuongRepository;
        this.trongLuongResponseMapper = trongLuongResponseMapper;
    }


    @Override
    public List<TrongLuongResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<TrongLuongResponse> getAll() {
        List<TrongLuong> trongLuongList = trongLuongRepository.findAll();
        return trongLuongResponseMapper.listToDTO(trongLuongList);
    }

    @Override
    public TrongLuongResponse create(TrongLuongRequest trongLuongRequest) {
        return null;
    }

    @Override
    public TrongLuongResponse update(Long aLong, TrongLuongRequest trongLuongRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public TrongLuongResponse getById(Long aLong) {
        return null;
    }
}
