package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacRequest;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.mapper.mausac.request.MauSacRequestMapper;
import com.okconde.bestepstyle.core.mapper.mausac.response.MauSacResponseMapper;
import com.okconde.bestepstyle.core.repository.MauSacRepository;
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

        MauSac ms = new MauSac();
        ms.setTenMau(mauSacRequest.getTenMau());
        ms.setGiaTri(mauSacRequest.getGiaTri());
        ms.setMoTa(mauSacRequest.getMoTa());
        ms.setTrangThai(mauSacRequest.getTrangThai());
        MauSac savems = mauSacRepository.save(ms);
        return mauSacResponseMapper.toDTO(savems);
    }

    @Override
    public MauSacResponse update(Long aLong, MauSacRequest mauSacRequest) {
        MauSac ms = mauSacRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Màu sắc không tồn tại"));
        ms.setTenMau(mauSacRequest.getTenMau());
        ms.setGiaTri(mauSacRequest.getGiaTri());
        ms.setMoTa(mauSacRequest.getMoTa());
        ms.setTrangThai(mauSacRequest.getTrangThai());
        MauSac updatems = mauSacRepository.save(ms);
        return mauSacResponseMapper.toDTO(updatems);
    }

    @Override
    public void delete(Long aLong) {
        if (!mauSacRepository.existsById(aLong)){
            throw new IllegalArgumentException("Màu sắc không tồn tại");
        }
        mauSacRepository.deleteById(aLong);
        System.out.println("Đã xóa thành công màu sắc với ID: " + aLong);
    }

    @Override
    public MauSacResponse getById(Long aLong) {
        MauSac ms = mauSacRepository.findById(aLong).orElseThrow(() ->
            new IllegalArgumentException("Màu sắc không tồn tại id"));
        return mauSacResponseMapper.toDTO(ms);
    }
}
