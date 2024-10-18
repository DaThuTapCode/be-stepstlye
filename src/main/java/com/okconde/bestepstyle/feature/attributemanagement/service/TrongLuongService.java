package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacSearchRequest;
import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
import com.okconde.bestepstyle.core.dto.trongluong.request.TrongLuongRequest;
import com.okconde.bestepstyle.core.dto.trongluong.request.TrongLuongSearchRequest;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.entity.TrongLuong;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.trongluong.request.TrongLuongRequestMapper;
import com.okconde.bestepstyle.core.mapper.trongluong.response.TrongLuongResponseMapper;
import com.okconde.bestepstyle.core.repository.TrongLuongRepository;
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
public class TrongLuongService implements IBaseService<TrongLuong, Long, TrongLuongRequest, TrongLuongResponse> {

    private final TrongLuongRepository trongLuongRepository;

    private final TrongLuongResponseMapper trongLuongResponseMapper;

    private final TrongLuongRequestMapper trongLuongRequestMapper;
    public TrongLuongService(
            TrongLuongRepository trongLuongRepository,
            TrongLuongResponseMapper trongLuongResponseMapper,
            TrongLuongRequestMapper trongLuongRequestMapper) {
        this.trongLuongRepository = trongLuongRepository;
        this.trongLuongResponseMapper = trongLuongResponseMapper;
        this.trongLuongRequestMapper = trongLuongRequestMapper;
    }


    @Override
    public List<TrongLuongResponse> getPage(Pageable pageable) {
        return trongLuongRepository.findAll(pageable).map(trongLuongResponseMapper ::toDTO).getContent();
    }

    @Override
    public List<TrongLuongResponse> getAll() {
        List<TrongLuong> trongLuongList = trongLuongRepository.findAll();
        return trongLuongResponseMapper.listToDTO(trongLuongList);
    }

    @Override
    @Transactional
    public TrongLuongResponse create(TrongLuongRequest trongLuongRequest) {
        TrongLuong entity = trongLuongRequestMapper.toEntity(trongLuongRequest);
        entity.setTrangThai(StatusEnum.ACTIVE);
        TrongLuong trongLuong = trongLuongRepository.save(entity);
        return trongLuongResponseMapper.toDTO(trongLuong);
    }

    @Override
    @Transactional
    public TrongLuongResponse update(Long id, TrongLuongRequest trongLuongRequest) {
        TrongLuong trongLuong = trongLuongRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Không tìm thấy trọng lượng với id" + id));

        trongLuong.setMaTrongLuong(trongLuongRequest.getMaTrongLuong());
        trongLuong.setGiaTri(trongLuongRequest.getGiaTri());
        trongLuong.setMoTa(trongLuongRequest.getMoTa());
        trongLuong.setTrangThai(trongLuongRequest.getTrangThai());
        TrongLuong trongLuongUpdated = trongLuongRepository.save(trongLuong);
        return trongLuongResponseMapper.toDTO(trongLuongUpdated);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        Optional<TrongLuong> optionalTrongLuong = trongLuongRepository.findById(id);
        if (optionalTrongLuong.isPresent()){
            TrongLuong trongLuong = optionalTrongLuong.get();
            trongLuong.setTrangThai(StatusEnum.INACTIVE);
            trongLuongRepository.save(trongLuong);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + id);
        }
    }

    @Override
    public TrongLuongResponse getById(Long aLong) {
        TrongLuong tl = trongLuongRepository.findById(aLong).orElseThrow(() ->
                new ResourceNotFoundException("Trọng lượng không tồn tại id"));
        return trongLuongResponseMapper.toDTO(tl);
    }

    public Page<TrongLuongResponse> searchPageTrongLuong(Pageable pageable, TrongLuongSearchRequest trongLuongSearchRequest){
        Page<TrongLuong> trongLuongPage = trongLuongRepository.searchPageTrongLuong(pageable,
                trongLuongSearchRequest.getMaTrongLuong(),
                trongLuongSearchRequest.getGiaTri()
        );
        return trongLuongPage.map(trongLuongResponseMapper::toDTO);
    }
}
