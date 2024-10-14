package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacRequest;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacSearchRequest;
import com.okconde.bestepstyle.core.entity.KichCo;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.mausac.request.MauSacRequestMapper;
import com.okconde.bestepstyle.core.mapper.mausac.response.MauSacResponseMapper;
import com.okconde.bestepstyle.core.repository.MauSacRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
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
public class MauSacService implements IBaseService <MauSac, Long, MauSacRequest, MauSacResponse> {
    private final MauSacRepository mauSacRepository;

    private final MauSacResponseMapper mauSacResponseMapper;

    private final MauSacRequestMapper mauSacRequestMapper;
    public MauSacService(
            MauSacRepository mauSacRepository,
            MauSacResponseMapper mauSacResponseMapper,
            MauSacRequestMapper mauSacRequestMapper) {
        this.mauSacRepository = mauSacRepository;
        this.mauSacResponseMapper = mauSacResponseMapper;
        this.mauSacRequestMapper = mauSacRequestMapper;
    }

    @Override
    public List<MauSacResponse> getPage(Pageable pageable) {
        return mauSacRepository.findAll(pageable).map(mauSacResponseMapper ::toDTO).getContent();
    }

    @Override
    public List<MauSacResponse> getAll() {
        List<MauSac> mauSacList = mauSacRepository.findAll();
        return mauSacResponseMapper.listToDTO(mauSacList);
    }

    @Override
    @Transactional
    public MauSacResponse create(MauSacRequest mauSacRequest) {
        MauSac entity = mauSacRequestMapper.toEntity(mauSacRequest);
        entity.setTrangThai(StatusEnum.ACTIVE);
        MauSac mauSac = mauSacRepository.save(entity);
        return mauSacResponseMapper.toDTO(mauSac);
    }

    @Override
    @Transactional
    public MauSacResponse update(Long id, MauSacRequest mauSacRequest) {
        MauSac mauSac = mauSacRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Không tìm thấy màu sắc với id" + id));

        mauSac.setTenMau(mauSacRequest.getTenMau());
        mauSac.setGiaTri(mauSacRequest.getGiaTri());
        mauSac.setMoTa(mauSacRequest.getMoTa());
        mauSac.setTrangThai(StatusEnum.ACTIVE);
        mauSac.setMaMauSac(mauSacRequest.getMaMauSac());
        mauSac.setTenMau(mauSacRequest.getTenMau());
        mauSac.setGiaTri(mauSacRequest.getGiaTri());
        mauSac.setMoTa(mauSacRequest.getMoTa());
        mauSac.setTrangThai(mauSacRequest.getTrangThai());
        MauSac mauSacUpdated = mauSacRepository.save(mauSac);
        return mauSacResponseMapper.toDTO(mauSacUpdated);
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        Optional<MauSac> optionalMauSac = mauSacRepository.findById(aLong);
        if (optionalMauSac.isPresent()){
            MauSac mauSac = optionalMauSac.get();
            mauSac.setTrangThai(StatusEnum.INACTIVE);
            mauSacRepository.save(mauSac);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + aLong);
        }
    }

    @Override
    public MauSacResponse getById(Long aLong) {
        MauSac ms = mauSacRepository.findById(aLong).orElseThrow(() ->
            new ResourceNotFoundException("Màu sắc không tồn tại id"));
        return mauSacResponseMapper.toDTO(ms);
    }

//    public Page<MauSacResponse> searchPageMS(Pageable pageable){
//
//        Page<MauSac> mauSacPage = mauSacRepository.searchPageMSByTenAndGT(pageable);
//        return mauSacPage.map(mauSacResponseMapper::toDTO);
//    }


}
