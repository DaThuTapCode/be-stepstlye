package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.dto.kichco.request.KichCoRequest;
import com.okconde.bestepstyle.core.entity.KichCo;
import com.okconde.bestepstyle.core.mapper.kichco.response.KichCoResponseMapper;
import com.okconde.bestepstyle.core.repository.KichCoRepository;
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
public class KichCoService implements IBaseService<KichCo, Long, KichCoRequest, KichCoResponse> {
    private final KichCoRepository kichCoRepository;

    private final KichCoResponseMapper kichCoResponseMapper;

    public KichCoService(KichCoRepository kichCoRepository, KichCoResponseMapper kichCoResponseMapper) {
        this.kichCoRepository = kichCoRepository;
        this.kichCoResponseMapper = kichCoResponseMapper;
    }


    @Override
    public List<KichCoResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<KichCoResponse> getAll() {
        List<KichCo> kichCoList = kichCoRepository.findAll();
        return kichCoResponseMapper.listToDTO(kichCoList);
    }

    @Override
    public KichCoResponse create(KichCoRequest kichCoRequest) {
        return null;
    }

    @Override
    public KichCoResponse update(Long aLong, KichCoRequest kichCoRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public KichCoResponse getById(Long aLong) {
        return null;
    }
}
