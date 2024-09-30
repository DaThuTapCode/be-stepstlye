package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
import com.okconde.bestepstyle.core.entity.HoaDonChiTiet;
import com.okconde.bestepstyle.core.entity.PhieuGiamGia;
import com.okconde.bestepstyle.core.mapper.phieugiamgia.response.PhieuGiamGiaResponseMapper;
import com.okconde.bestepstyle.core.repository.PhieuGiamGiaRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by TuanIf on 9/25/2024 20:28:25
 *
 * @author TuanIf
 */

@Service(value = "PhieuGiamGiaService")

public class PhieuGiamGiaService implements IBaseService<PhieuGiamGia, Long, PhieuGiamGiaRequest, PhieuGiamGiaResponse> {

    private final PhieuGiamGiaResponseMapper phieuGiamGiaResponseMapper;

    private final PhieuGiamGiaRepository phieuGiamGiaRepository;

    public PhieuGiamGiaService(PhieuGiamGiaResponseMapper phieuGiamGiaResponseMapper, PhieuGiamGiaRepository phieuGiamGiaRepository) {
        this.phieuGiamGiaResponseMapper = phieuGiamGiaResponseMapper;
        this.phieuGiamGiaRepository = phieuGiamGiaRepository;
    }


    @Override
    public List<PhieuGiamGiaResponse> getPage(Pageable pageable) {
        Page<PhieuGiamGia> page = phieuGiamGiaRepository.findAll(pageable);
        return page.map(phieuGiamGiaResponseMapper::toDTO).getContent();
    }


    @Override
    public List<PhieuGiamGiaResponse> getAll() {
        List<PhieuGiamGia> phieuGiamGiaList = phieuGiamGiaRepository.findAll();
        return phieuGiamGiaResponseMapper.listToDTO(phieuGiamGiaList);
    }

    @Override
    public PhieuGiamGiaResponse create(PhieuGiamGiaRequest phieuGiamGiaRequest) {
        PhieuGiamGia phieuGiamGia = phieuGiamGiaResponseMapper.toEntity(phieuGiamGiaRequest);
        phieuGiamGia = phieuGiamGiaRepository.save(phieuGiamGia);
        return phieuGiamGiaResponseMapper.toDTO(phieuGiamGia);
    }

    @Override
    public PhieuGiamGiaResponse update(Long id, PhieuGiamGiaRequest phieuGiamGiaRequest) {
        Optional<PhieuGiamGia> optionalPhieuGiamGia = phieuGiamGiaRepository.findById(id);
        if(optionalPhieuGiamGia.isPresent()) {
            PhieuGiamGia phieuGiamGia = optionalPhieuGiamGia.get();
            // Update các trường từ Resquet
            phieuGiamGia = phieuGiamGiaResponseMapper.toEntity(phieuGiamGiaRequest);
            phieuGiamGia.setIdPhieuGiamGia(id);
            phieuGiamGia = phieuGiamGiaRepository.save(phieuGiamGia);
            return phieuGiamGiaResponseMapper.toDTO(phieuGiamGia);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id" + id);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<PhieuGiamGia> optionalPhieuGiamGia = phieuGiamGiaRepository.findById(id);
        if (optionalPhieuGiamGia.isPresent()){
            PhieuGiamGia phieuGiamGia = optionalPhieuGiamGia.get();
            phieuGiamGia.setDeleted(true);
            phieuGiamGiaRepository.save(phieuGiamGia);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id: " + id);
        }
    }

    @Override
    public PhieuGiamGiaResponse getById(Long id) {
        Optional<PhieuGiamGia> optionalPhieuGiamGia = phieuGiamGiaRepository.findById(id);
        if (optionalPhieuGiamGia.isPresent() && optionalPhieuGiamGia.get().isDeleted()) {
            return phieuGiamGiaResponseMapper.toDTO(optionalPhieuGiamGia.get());
        } else {
            throw new EntityNotFoundException("Không tìm thấy id: " + id);
        }
    }
}
