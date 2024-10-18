package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaSearchRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
import com.okconde.bestepstyle.core.entity.PhieuGiamGia;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.phieugiamgia.request.PhieuGiamGiaRequestMapper;
import com.okconde.bestepstyle.core.mapper.phieugiamgia.response.PhieuGiamGiaResponseMapper;
import com.okconde.bestepstyle.core.repository.PhieuGiamGiaRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.enumutil.StatusPhieuGiamGia;
import com.okconde.bestepstyle.core.util.formater.DateFormater;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by TuanIf on 9/25/2024 20:28:25
 *
 * @author TuanIf
 */

@Service(value = "PhieuGiamGiaService")

public class PhieuGiamGiaService implements IBaseService<PhieuGiamGia, Long, PhieuGiamGiaRequest, PhieuGiamGiaResponse> {

    //Repo
    private final PhieuGiamGiaRepository phieuGiamGiaRepository;

    //Mapper
    private final PhieuGiamGiaRequestMapper phieuGiamGiaRequestMapper;
    private final PhieuGiamGiaResponseMapper phieuGiamGiaResponseMapper;

    public PhieuGiamGiaService(PhieuGiamGiaResponseMapper phieuGiamGiaResponseMapper, PhieuGiamGiaRepository phieuGiamGiaRepository, PhieuGiamGiaRequestMapper phieuGiamGiaRequestMapper) {
        this.phieuGiamGiaResponseMapper = phieuGiamGiaResponseMapper;
        this.phieuGiamGiaRepository = phieuGiamGiaRepository;
        this.phieuGiamGiaRequestMapper = phieuGiamGiaRequestMapper;
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
        PhieuGiamGia phieuGiamGiaNew = phieuGiamGiaRequestMapper.toEntity(phieuGiamGiaRequest);
        phieuGiamGiaNew.setTenPhieuGiamGia(phieuGiamGiaNew.getTenPhieuGiamGia());
        phieuGiamGiaNew.setMoTa(phieuGiamGiaNew.getMoTa());
        phieuGiamGiaNew.setLoaiGiam(phieuGiamGiaNew.getLoaiGiam());
        phieuGiamGiaNew.setNgayBatDau(LocalDateTime.now());
        phieuGiamGiaNew.setNgayKetThuc(LocalDateTime.now());
        phieuGiamGiaNew.setGiaTriGiamToiDa(phieuGiamGiaNew.getGiaTriGiamToiThieu());
        phieuGiamGiaNew.setGiaTriGiam(phieuGiamGiaNew.getGiaTriGiam());
        phieuGiamGiaNew.setGiaTriGiamToiThieu(phieuGiamGiaNew.getGiaTriGiamToiDa());
        phieuGiamGiaNew.setTrangThai(StatusPhieuGiamGia.ACTIVE);

        PhieuGiamGia phieuGiamGiaSaved = phieuGiamGiaRepository.save(phieuGiamGiaNew);
        return phieuGiamGiaResponseMapper.toDTO(phieuGiamGiaSaved);
    }

    @Override
    @Transactional
    public PhieuGiamGiaResponse update(Long id, PhieuGiamGiaRequest phieuGiamGiaRequest) {
        PhieuGiamGia phieuGiamGiaExisting = phieuGiamGiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy với id: " + id));

        phieuGiamGiaExisting.setTenPhieuGiamGia(phieuGiamGiaRequest.getTenPhieuGiamGia());
        phieuGiamGiaExisting.setMoTa(phieuGiamGiaRequest.getMoTa());
        phieuGiamGiaExisting.setLoaiGiam(phieuGiamGiaRequest.getLoaiGiam());
        phieuGiamGiaExisting.setNgayBatDau(LocalDateTime.now());
        phieuGiamGiaExisting.setNgayKetThuc(LocalDateTime.now());
        phieuGiamGiaExisting.setGiaTriGiamToiDa(phieuGiamGiaRequest.getGiaTriGiamToiDa());
        phieuGiamGiaExisting.setGiaTriGiam(phieuGiamGiaRequest.getGiaTriGiam());
        phieuGiamGiaExisting.setGiaTriGiamToiThieu(phieuGiamGiaRequest.getGiaTriGiamToiThieu());

        PhieuGiamGia phieuGiamGiaUpdated = phieuGiamGiaRepository.save(phieuGiamGiaExisting);
        return phieuGiamGiaResponseMapper.toDTO(phieuGiamGiaUpdated);
    }

    @Override
    public void delete(Long id) {
        Optional<PhieuGiamGia> optionalPhieuGiamGia = phieuGiamGiaRepository.findById(id);
        if (optionalPhieuGiamGia.isPresent()) {
            PhieuGiamGia phieuGiamGia = optionalPhieuGiamGia.get();
            phieuGiamGia.setTrangThai(StatusPhieuGiamGia.ACTIVE);
            phieuGiamGiaRepository.save(phieuGiamGia);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id: " + id);
        }
    }

    @Override
    public PhieuGiamGiaResponse getById(Long id) {
        PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy với id: " + id));
        return phieuGiamGiaResponseMapper.toDTO(phieuGiamGia);
    }

    public Page<PhieuGiamGiaResponse> searchPagePhieuGiamGia(Pageable pageable, PhieuGiamGiaSearchRequest phieuGiamGiaSearchRequest){
        Page<PhieuGiamGia> phieuGiamGiaPage = phieuGiamGiaRepository.searchPagePhieuGiamGia(pageable,
                phieuGiamGiaSearchRequest.getMaPhieuGiamGia(),
                phieuGiamGiaSearchRequest.getTenPhieuGiamGia(),
                phieuGiamGiaSearchRequest.getNgayBatDau(),
                DateFormater.setEndDate(phieuGiamGiaSearchRequest.getNgayKetThuc()),
                phieuGiamGiaSearchRequest.getLoaiGiam()
        );
        return phieuGiamGiaPage.map(phieuGiamGiaResponseMapper::toDTO);
    }
}
