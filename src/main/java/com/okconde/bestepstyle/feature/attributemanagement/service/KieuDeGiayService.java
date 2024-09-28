package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.kieudegiay.reponse.KieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.kieudegiay.request.KieuDeGiayRequest;
import com.okconde.bestepstyle.core.entity.KieuDeGiay;
import com.okconde.bestepstyle.core.entity.MauSac;
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
        List<KieuDeGiay> kieuDeGiayList = kieuDeGiayRepository.findAll(pageable).getContent();
        return kieuDeGiayResponseMapper.listToDTO(kieuDeGiayList);
    }

    @Override
    public List<KieuDeGiayResponse> getAll() {
        List<KieuDeGiay> kieuDeGiayList = kieuDeGiayRepository.findAll();
        return kieuDeGiayResponseMapper.listToDTO(kieuDeGiayList);
    }

    @Override
    public KieuDeGiayResponse create(KieuDeGiayRequest kieuDeGiayRequest) {
        KieuDeGiay kdg = new KieuDeGiay();
        kdg.setTenKieuDeGiay(kieuDeGiayRequest.getTenKieuDeGiay());
        kdg.setGiaTri(kieuDeGiayRequest.getGiaTri());
        kdg.setMoTa(kieuDeGiayRequest.getMoTa());
        kdg.setTrangThai(kieuDeGiayRequest.getTrangThai());
        KieuDeGiay savekdg = kieuDeGiayRepository.save(kdg);
        return kieuDeGiayResponseMapper.toDTO(savekdg);
    }

    @Override
    public KieuDeGiayResponse update(Long aLong, KieuDeGiayRequest kieuDeGiayRequest) {
        KieuDeGiay kdg = kieuDeGiayRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Kiểu đế giày không tồn tại"));
        kdg.setTenKieuDeGiay(kieuDeGiayRequest.getTenKieuDeGiay());
        kdg.setGiaTri(kieuDeGiayRequest.getGiaTri());
        kdg.setMoTa(kieuDeGiayRequest.getMoTa());
        kdg.setTrangThai(kieuDeGiayRequest.getTrangThai());
        KieuDeGiay updatekdg = kieuDeGiayRepository.save(kdg);
        return kieuDeGiayResponseMapper.toDTO(updatekdg);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public KieuDeGiayResponse getById(Long aLong) {
        KieuDeGiay kdg = kieuDeGiayRepository.findById(aLong).orElseThrow(() ->
                new IllegalArgumentException("Kiểu đế giày không tồn tại id"));
        return kieuDeGiayResponseMapper.toDTO(kdg);
    }
}
