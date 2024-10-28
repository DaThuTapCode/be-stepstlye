package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.kieudegiay.reponse.KieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.kieudegiay.request.KieuDeGiayRequest;
import com.okconde.bestepstyle.core.dto.kieudegiay.request.KieuDeGiaySearchRequest;
import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacSearchRequest;
import com.okconde.bestepstyle.core.entity.KieuDeGiay;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.exception.AttributeCodeDuplicateException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.kieudegiay.request.KieuDeGiayRequestMapper;
import com.okconde.bestepstyle.core.mapper.kieudegiay.response.KieuDeGiayResponseMapper;
import com.okconde.bestepstyle.core.repository.KieuDeGiayRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
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
public class KieuDeGiayService implements IBaseService<KieuDeGiay, Long, KieuDeGiayRequest, KieuDeGiayResponse> {

    private final KieuDeGiayRepository kieuDeGiayRepository;

    private final KieuDeGiayResponseMapper kieuDeGiayResponseMapper;
    private final KieuDeGiayRequestMapper kieuDeGiayRequestMapper;

    public KieuDeGiayService(KieuDeGiayRepository kieuDeGiayRepository,
                             KieuDeGiayResponseMapper kieuDeGiayResponseMapper,

                             KieuDeGiayRequestMapper kieuDeGiayRequestMapper) {
        this.kieuDeGiayRepository = kieuDeGiayRepository;
        this.kieuDeGiayResponseMapper = kieuDeGiayResponseMapper;
        this.kieuDeGiayRequestMapper = kieuDeGiayRequestMapper;
    }

    @Override
    public List<KieuDeGiayResponse> getPage(Pageable pageable) {
        return kieuDeGiayRepository.findAll(pageable).map(kieuDeGiayResponseMapper ::toDTO).getContent();
    }

    @Override
    public List<KieuDeGiayResponse> getAll() {
        List<KieuDeGiay> kieuDeGiayList = kieuDeGiayRepository.findAll();
        return kieuDeGiayResponseMapper.listToDTO(kieuDeGiayList);
    }

    @Override
    @Transactional
    public KieuDeGiayResponse create(KieuDeGiayRequest kieuDeGiayRequest) {
        kieuDeGiayRequest.setMaKieuDeGiay(GenerateCodeRandomUtil.generateProductCode("KDG", 7));
        if (kieuDeGiayRepository.getKieuDeGiayByMaKieuDeGiay(kieuDeGiayRequest.getMaKieuDeGiay()).isPresent()){
            throw new AttributeCodeDuplicateException("Mã kiểu đế giày " + kieuDeGiayRequest.getMaKieuDeGiay() + " đã tồn tại");
        }
        KieuDeGiay entity = kieuDeGiayRequestMapper.toEntity(kieuDeGiayRequest);
        entity.setTrangThai(StatusEnum.ACTIVE);
        KieuDeGiay kieuDeGiay = kieuDeGiayRepository.save(entity);
        return kieuDeGiayResponseMapper.toDTO(kieuDeGiay);
    }

    @Override
    @Transactional
    public KieuDeGiayResponse update(Long id, KieuDeGiayRequest kieuDeGiayRequest) {
        KieuDeGiay kieuDeGiay = kieuDeGiayRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Không tìm thấy kiểu đế giầy với id" + id));

        kieuDeGiay.setMaKieuDeGiay(kieuDeGiayRequest.getMaKieuDeGiay());
        kieuDeGiay.setTenKieuDeGiay(kieuDeGiayRequest.getTenKieuDeGiay());
        kieuDeGiay.setGiaTri(kieuDeGiayRequest.getGiaTri());
        kieuDeGiay.setMoTa(kieuDeGiayRequest.getMoTa());
        kieuDeGiay.setTrangThai(kieuDeGiayRequest.getTrangThai());
        KieuDeGiay kieuDeGiayUpdated = kieuDeGiayRepository.save(kieuDeGiay);
        return kieuDeGiayResponseMapper.toDTO(kieuDeGiayUpdated);
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        Optional<KieuDeGiay> optionalKieuDeGiay = kieuDeGiayRepository.findById(aLong);
        if (optionalKieuDeGiay.isPresent()){
            KieuDeGiay kieuDeGiay = optionalKieuDeGiay.get();
            kieuDeGiay.setTrangThai(StatusEnum.INACTIVE);
            kieuDeGiayRepository.save(kieuDeGiay);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + aLong);
        }
    }

    @Override
    public KieuDeGiayResponse getById(Long aLong) {
        KieuDeGiay kdg = kieuDeGiayRepository.findById(aLong).orElseThrow(() ->
                new ResourceNotFoundException("Kiểu đế giày không tồn tại id"));
        return kieuDeGiayResponseMapper.toDTO(kdg);
    }

    public Page<KieuDeGiayResponse> searchPageKieuDeGiay(Pageable pageable, KieuDeGiaySearchRequest kieuDeGiaySearchRequest){
        Page<KieuDeGiay> kieuDeGiayPage = kieuDeGiayRepository.searchPageKieuDeGiay(pageable,
                kieuDeGiaySearchRequest.getMaKieuDeGiay(),
                kieuDeGiaySearchRequest.getTenKieuDeGiay()
        );
        return kieuDeGiayPage.map(kieuDeGiayResponseMapper::toDTO);
    }
}
