package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
import com.okconde.bestepstyle.core.dto.trongluong.request.TrongLuongRequest;
import com.okconde.bestepstyle.core.entity.KichCo;
import com.okconde.bestepstyle.core.entity.TrongLuong;
import com.okconde.bestepstyle.core.mapper.trongluong.response.TrongLuongResponseMapper;
import com.okconde.bestepstyle.core.repository.TrongLuongRepository;
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
public class TrongLuongService implements IBaseService<TrongLuong, Long, TrongLuongRequest, TrongLuongResponse> {

    private final TrongLuongRepository trongLuongRepository;

    private final TrongLuongResponseMapper trongLuongResponseMapper;

    public TrongLuongService(
            TrongLuongRepository trongLuongRepository,
            TrongLuongResponseMapper trongLuongResponseMapper
    ) {
        this.trongLuongRepository = trongLuongRepository;
        this.trongLuongResponseMapper = trongLuongResponseMapper;
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
    public TrongLuongResponse create(TrongLuongRequest trongLuongRequest) {
        TrongLuong entity = trongLuongResponseMapper.toEntity(trongLuongRequest);
        TrongLuong trongLuong = trongLuongRepository.save(entity);
        return trongLuongResponseMapper.toDTO(trongLuong);
    }

    @Override
    public TrongLuongResponse update(Long aLong, TrongLuongRequest trongLuongRequest) {
        Optional<TrongLuong> optionalTrongLuong = trongLuongRepository.findById(aLong);
//        if(optionalTrongLuong.isPresent() && !optionalTrongLuong.get().isDeleted()) {
//            TrongLuong trongLuong = optionalTrongLuong.get();
//            trongLuong = trongLuongResponseMapper.toEntity(trongLuongRequest);
//            trongLuong.setIdTrongLuong(aLong);
//            trongLuong = trongLuongRepository.save(trongLuong);
//            return trongLuongResponseMapper.toDTO(trongLuong);
//        } else {
//            throw new EntityNotFoundException("Không tìm thấy id" + aLong);
//        }
        return null;
    }


    @Override
    public void delete(Long id) {
        Optional<TrongLuong> optionalTrongLuong = trongLuongRepository.findById(id);
        if (optionalTrongLuong.isPresent()){
            TrongLuong trongLuong = optionalTrongLuong.get();
            //trongLuong.setDeleted(true);
            trongLuongRepository.save(trongLuong);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + id);
        }
    }

    @Override
    public TrongLuongResponse getById(Long aLong) {
        TrongLuong tl = trongLuongRepository.findById(aLong).orElseThrow(() ->
                new IllegalArgumentException("Trọng lượng không tồn tại id"));
        return trongLuongResponseMapper.toDTO(tl);
    }
}
