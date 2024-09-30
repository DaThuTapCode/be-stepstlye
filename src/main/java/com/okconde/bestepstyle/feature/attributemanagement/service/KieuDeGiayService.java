package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.kieudegiay.reponse.KieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.kieudegiay.request.KieuDeGiayRequest;
import com.okconde.bestepstyle.core.entity.KieuDeGiay;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.mapper.kieudegiay.response.KieuDeGiayResponseMapper;
import com.okconde.bestepstyle.core.repository.KieuDeGiayRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        KieuDeGiay entity = kieuDeGiayResponseMapper.toEntity(kieuDeGiayRequest);
        KieuDeGiay kieuDeGiay = kieuDeGiayRepository.save(entity);
        return kieuDeGiayResponseMapper.toDTO(kieuDeGiay);
    }

    @Override
    public KieuDeGiayResponse update(Long aLong, KieuDeGiayRequest kieuDeGiayRequest) {
        Optional<KieuDeGiay> optionalKieuDeGiay = kieuDeGiayRepository.findById(aLong);
        if(optionalKieuDeGiay.isPresent() && !optionalKieuDeGiay.get().isDeleted()) {
            KieuDeGiay kieuDeGiay = optionalKieuDeGiay.get();
            // Update các trường từ Resquet
            kieuDeGiay = kieuDeGiayResponseMapper.toEntity(kieuDeGiayRequest);
            kieuDeGiay.setIdKieuDeGiay(aLong);
            kieuDeGiay = kieuDeGiayRepository.save(kieuDeGiay);
            return kieuDeGiayResponseMapper.toDTO(kieuDeGiay);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id" + aLong);
        }
    }

    @Override
    public void delete(Long aLong) {
        Optional<KieuDeGiay> optionalKieuDeGiay = kieuDeGiayRepository.findById(aLong);
        if (optionalKieuDeGiay.isPresent()){
            KieuDeGiay kieuDeGiay = optionalKieuDeGiay.get();
            kieuDeGiay.setDeleted(true);
            kieuDeGiayRepository.save(kieuDeGiay);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + aLong);
        }
    }

    @Override
    public KieuDeGiayResponse getById(Long aLong) {
        KieuDeGiay kdg = kieuDeGiayRepository.findById(aLong).orElseThrow(() ->
                new IllegalArgumentException("Kiểu đế giày không tồn tại id"));
        return kieuDeGiayResponseMapper.toDTO(kdg);
    }
}
