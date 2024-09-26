package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.kieudegiay.reponse.KieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.kieudegiay.request.KieuDeGiayRequest;
import com.okconde.bestepstyle.core.entity.KieuDeGiay;
import com.okconde.bestepstyle.core.mapper.kieudegiay.response.KieuDeGiayResponseMapper;
import com.okconde.bestepstyle.core.repository.KieuDeGiayRepository;
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
public class KieuDeGiayService implements IBaseService<KieuDeGiay, Long, KieuDeGiayRequest, KieuDeGiayResponse> {

    private final KieuDeGiayRepository kieuDeGiayRepository;

    private final KieuDeGiayResponseMapper kieuDeGiayResponseMapper;

    public KieuDeGiayService(KieuDeGiayRepository kieuDeGiayRepository,
                             KieuDeGiayResponseMapper kieuDeGiayResponseMapper
    ) {
        this.kieuDeGiayRepository = kieuDeGiayRepository;
        this.kieuDeGiayResponseMapper = kieuDeGiayResponseMapper;
    }

    @Override
    public List<KieuDeGiayResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<KieuDeGiayResponse> getAll() {
        List<KieuDeGiay> kieuDeGiayList = kieuDeGiayRepository.findAll();
        return kieuDeGiayResponseMapper.listToDTO(kieuDeGiayList);
    }

    @Override
    public KieuDeGiayResponse create(KieuDeGiayRequest kieuDeGiayRequest) {
        return null;
    }

    @Override
    public KieuDeGiayResponse update(Long aLong, KieuDeGiayRequest kieuDeGiayRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public KieuDeGiayResponse getById(Long aLong) {
        return null;
    }
}
