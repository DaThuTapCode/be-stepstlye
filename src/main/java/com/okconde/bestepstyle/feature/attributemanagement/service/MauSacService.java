package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacRequest;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.entity.TrongLuong;
import com.okconde.bestepstyle.core.mapper.mausac.request.MauSacRequestMapper;
import com.okconde.bestepstyle.core.mapper.mausac.response.MauSacResponseMapper;
import com.okconde.bestepstyle.core.repository.MauSacRepository;
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
public class MauSacService implements IBaseService <MauSac, Long, MauSacRequest, MauSacResponse> {
    private final MauSacRepository mauSacRepository;

    private final MauSacResponseMapper mauSacResponseMapper;

    public MauSacService(
            MauSacRepository mauSacRepository,
            MauSacResponseMapper mauSacResponseMapper
    ) {
        this.mauSacRepository = mauSacRepository;
        this.mauSacResponseMapper = mauSacResponseMapper;
    }

    @Override
    public List<MauSacResponse> getPage(Pageable pageable) {
        List<MauSac> mauSacList = mauSacRepository.findAll(pageable).getContent();
        return mauSacResponseMapper.listToDTO(mauSacList);
    }


    @Override
    public List<MauSacResponse> getAll() {
        List<MauSac> mauSacList = mauSacRepository.findAll();
        return mauSacResponseMapper.listToDTO(mauSacList);
    }

    @Override
    public MauSacResponse create(MauSacRequest mauSacRequest) {
        MauSac entity = mauSacResponseMapper.toEntity(mauSacRequest);
        MauSac mauSac = mauSacRepository.save(entity);
        return mauSacResponseMapper.toDTO(mauSac);
    }

    @Override
    public MauSacResponse update(Long aLong, MauSacRequest mauSacRequest) {
        Optional<MauSac> optionalMauSac = mauSacRepository.findById(aLong);
//        if(optionalMauSac.isPresent() && !optionalMauSac.get().isDeleted()) {
//            MauSac mauSac = optionalMauSac.get();
//            mauSac = mauSacResponseMapper.toEntity(mauSacRequest);
//            mauSac.setIdMauSac(aLong);
//            mauSac = mauSacRepository.save(mauSac);
//            return mauSacResponseMapper.toDTO(mauSac);
//        } else {
//            throw new EntityNotFoundException("Không tìm thấy id" + aLong);
//        }
        return null;
    }

    @Override
    public void delete(Long aLong) {
        Optional<MauSac> optionalMauSac = mauSacRepository.findById(aLong);
        if (optionalMauSac.isPresent()){
            MauSac mauSac = optionalMauSac.get();
            //mauSac.setDeleted(true);
            mauSacRepository.save(mauSac);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + aLong);
        }
    }

    @Override
    public MauSacResponse getById(Long aLong) {
        MauSac ms = mauSacRepository.findById(aLong).orElseThrow(() ->
            new IllegalArgumentException("Màu sắc không tồn tại id"));
        return mauSacResponseMapper.toDTO(ms);
    }
}
