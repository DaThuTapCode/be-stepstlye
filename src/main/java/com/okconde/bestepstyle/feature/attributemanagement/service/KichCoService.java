package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.dto.kichco.request.KichCoRequest;
import com.okconde.bestepstyle.core.entity.KichCo;
import com.okconde.bestepstyle.core.entity.KieuDeGiay;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.mapper.kichco.response.KichCoResponseMapper;
import com.okconde.bestepstyle.core.repository.KichCoRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        KichCo entity = kichCoResponseMapper.toEntity(kichCoRequest);
        KichCo kichCo = kichCoRepository.save(entity);
        return kichCoResponseMapper.toDTO(kichCo);
    }

    @Override
    public KichCoResponse update(Long aLong, KichCoRequest kichCoRequest) {
        Optional<KichCo> optionalKichCo = kichCoRepository.findById(aLong);
//        if(optionalKichCo.isPresent() && !optionalKichCo.get().isDeleted()) {
//            KichCo kichCo = optionalKichCo.get();
//            kichCo = kichCoResponseMapper.toEntity(kichCoRequest);
//            kichCo.setIdKichCo(aLong);
//            kichCo = kichCoRepository.save(kichCo);
//            return kichCoResponseMapper.toDTO(kichCo);
//        } else {
//            throw new EntityNotFoundException("Không tìm thấy id" + aLong);
//        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        Optional<KichCo> optionalKichCo = kichCoRepository.findById(aLong);
        if (optionalKichCo.isPresent()){
            KichCo kichCo = optionalKichCo.get();
            kichCo.setTrangThai(StatusEnum.INACTIVE);
            kichCoRepository.save(kichCo);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + aLong);
        }
    }

    @Override
    public KichCoResponse getById(Long aLong) {
        KichCo kc = kichCoRepository.findById(aLong).orElseThrow(() ->
                new IllegalArgumentException("Kích cỡ không tồn tại id"));
        return kichCoResponseMapper.toDTO(kc);
    }
}
