package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.lichsuhoadon.request.LichSuHoaDonRequest;
import com.okconde.bestepstyle.core.dto.lichsuhoadon.response.LichSuHoaDonResponse;
import com.okconde.bestepstyle.core.entity.HoaDonChiTiet;
import com.okconde.bestepstyle.core.entity.LichSuHoaDon;
import com.okconde.bestepstyle.core.mapper.lichsuhoadon.response.LichSuHoaDonResponseMapper;
import com.okconde.bestepstyle.core.repository.LichSuHoaDonRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by TuanIf on 9/25/2024 20:28:15
 *
 * @author TuanIf
 */

@Service(value = "LichSuHoaDonService")

public class LichSuHoaDonService implements IBaseService<LichSuHoaDon, Long, LichSuHoaDonRequest, LichSuHoaDonResponse> {

    private final LichSuHoaDonResponseMapper lichSuHoaDonResponseMapper;

    private final LichSuHoaDonRepository lichSuHoaDonRepository;

    public LichSuHoaDonService(LichSuHoaDonResponseMapper lichSuHoaDonResponseMapper, LichSuHoaDonRepository lichSuHoaDonRepository) {
        this.lichSuHoaDonResponseMapper = lichSuHoaDonResponseMapper;
        this.lichSuHoaDonRepository = lichSuHoaDonRepository;
    }

    @Override
    public List<LichSuHoaDonResponse> getPage(Pageable pageable) {
        Page<LichSuHoaDon> page = lichSuHoaDonRepository.findAll(pageable);
        return page.map(lichSuHoaDonResponseMapper:: toDTO).getContent();
    }

    @Override
    public List<LichSuHoaDonResponse> getAll() {
        List<LichSuHoaDon> lichSuHoaDonList = lichSuHoaDonRepository.findAll();
        return lichSuHoaDonResponseMapper.listToDTO(lichSuHoaDonList);
    }

    @Override
    public LichSuHoaDonResponse create(LichSuHoaDonRequest lichSuHoaDonRequest) {
        LichSuHoaDon lichSuHoaDon = lichSuHoaDonResponseMapper.toEntity(lichSuHoaDonRequest);
        lichSuHoaDon = lichSuHoaDonRepository.save(lichSuHoaDon);
        return lichSuHoaDonResponseMapper.toDTO(lichSuHoaDon);
    }

    @Override
    @Transactional
    public LichSuHoaDonResponse update(Long id, LichSuHoaDonRequest lichSuHoaDonRequest) {
        Optional<LichSuHoaDon> optionalLichSuHoaDon = lichSuHoaDonRepository.findById(id);
        if(optionalLichSuHoaDon.isPresent()) {
            LichSuHoaDon lichSuHoaDon = optionalLichSuHoaDon.get();
            // Update các trường từ Resquet
            lichSuHoaDon = lichSuHoaDonResponseMapper.toEntity(lichSuHoaDonRequest);
            lichSuHoaDon.setIdLshd(id);
            lichSuHoaDon = lichSuHoaDonRepository.save(lichSuHoaDon);
            return lichSuHoaDonResponseMapper.toDTO(lichSuHoaDon);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id" + id);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<LichSuHoaDon> optionalLichSuHoaDon = lichSuHoaDonRepository.findById(id);
        if (optionalLichSuHoaDon.isPresent()){
            LichSuHoaDon lichSuHoaDon = optionalLichSuHoaDon.get();
            lichSuHoaDon.setDeleted(true);
            lichSuHoaDonRepository.save(lichSuHoaDon);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id: " + id);
        }
    }

    @Override
    public LichSuHoaDonResponse getById(Long id) {
        Optional<LichSuHoaDon> optionalLichSuHoaDon = lichSuHoaDonRepository.findById(id);
        if (optionalLichSuHoaDon.isPresent() && optionalLichSuHoaDon.get().isDeleted()) {
            return lichSuHoaDonResponseMapper.toDTO(optionalLichSuHoaDon.get());
        } else {
            throw new EntityNotFoundException("Không tìm thấy id: " + id);
        }
    }
}
