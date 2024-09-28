package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
import com.okconde.bestepstyle.core.dto.trongluong.request.TrongLuongRequest;
import com.okconde.bestepstyle.core.entity.KichCo;
import com.okconde.bestepstyle.core.entity.TrongLuong;
import com.okconde.bestepstyle.core.mapper.trongluong.response.TrongLuongResponseMapper;
import com.okconde.bestepstyle.core.repository.TrongLuongRepository;
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
        TrongLuong tl = new TrongLuong();
        tl.setGiaTri(trongLuongRequest.getGiaTri());
        tl.setMoTa(trongLuongRequest.getMoTa());
        tl.setTrangThai(trongLuongRequest.getTrangThai());
        TrongLuong savetl = trongLuongRepository.save(tl);
        return trongLuongResponseMapper.toDTO(savetl);
    }

    @Override
    public TrongLuongResponse update(Long aLong, TrongLuongRequest trongLuongRequest) {
        TrongLuong tl = trongLuongRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Trọng lượng không tồn tại"));
        tl.setGiaTri(trongLuongRequest.getGiaTri());
        tl.setMoTa(trongLuongRequest.getMoTa());
        tl.setTrangThai(trongLuongRequest.getTrangThai());
        TrongLuong updatetl = trongLuongRepository.save(tl);
        return trongLuongResponseMapper.toDTO(updatetl);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public TrongLuongResponse getById(Long aLong) {
        TrongLuong tl = trongLuongRepository.findById(aLong).orElseThrow(() ->
                new IllegalArgumentException("Trọng lượng không tồn tại id"));
        return trongLuongResponseMapper.toDTO(tl);
    }
}
