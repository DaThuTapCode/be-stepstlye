package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.dto.kichco.request.KichCoRequest;
import com.okconde.bestepstyle.core.entity.KichCo;
import com.okconde.bestepstyle.core.entity.MauSac;
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
        List<KichCo> kichCoList = kichCoRepository.findAll(pageable).getContent();
        return kichCoResponseMapper.listToDTO(kichCoList);
    }

    @Override
    public List<KichCoResponse> getAll() {
        List<KichCo> kichCoList = kichCoRepository.findAll();
        return kichCoResponseMapper.listToDTO(kichCoList);
    }

    @Override
    public KichCoResponse create(KichCoRequest kichCoRequest) {
        KichCo kc = new KichCo();
        kc.setGiaTri(kichCoRequest.getGiaTri());
        kc.setMoTa(kichCoRequest.getMoTa());
        kc.setTrangThai(kichCoRequest.getTrangThai());
        KichCo savekc = kichCoRepository.save(kc);
        return kichCoResponseMapper.toDTO(savekc);
    }

    @Override
    public KichCoResponse update(Long aLong, KichCoRequest kichCoRequest) {
        KichCo kc = kichCoRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Kích cỡ không tồn tại"));
        kc.setGiaTri(kichCoRequest.getGiaTri());
        kc.setMoTa(kichCoRequest.getMoTa());
        kc.setTrangThai(kichCoRequest.getTrangThai());
        KichCo updatekc = kichCoRepository.save(kc);
        return kichCoResponseMapper.toDTO(updatekc);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public KichCoResponse getById(Long aLong) {
        KichCo kc = kichCoRepository.findById(aLong).orElseThrow(() ->
                new IllegalArgumentException("Kích cỡ không tồn tại id"));
        return kichCoResponseMapper.toDTO(kc);
    }
}
