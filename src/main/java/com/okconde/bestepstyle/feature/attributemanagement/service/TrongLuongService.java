package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
import com.okconde.bestepstyle.core.dto.trongluong.request.TrongLuongRequest;
import com.okconde.bestepstyle.core.entity.TrongLuong;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.trongluong.request.TrongLuongRequestMapper;
import com.okconde.bestepstyle.core.mapper.trongluong.response.TrongLuongResponseMapper;
import com.okconde.bestepstyle.core.repository.TrongLuongRepository;
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
        List<TrongLuong> trongLuongList = trongLuongRepository.findAll(pageable).getContent();
        return trongLuongResponseMapper.listToDTO(trongLuongList);
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

        TrongLuong trongLuongUpdate = trongLuongRequestMapper.toEntity(trongLuongRequest);
        trongLuong.setGiaTri(trongLuongUpdate.getGiaTri());
        trongLuong.setMoTa(trongLuongUpdate.getMoTa());
        TrongLuong trongLuongUpdated1 = trongLuongRepository.save(trongLuongUpdate);
        return trongLuongResponseMapper.toDTO(trongLuongUpdated1);
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
}
