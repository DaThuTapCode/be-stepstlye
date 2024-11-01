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
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;;
import com.okconde.bestepstyle.core.util.enumutil.StatusPhieuGiamGia;
import com.okconde.bestepstyle.core.util.formater.DateFormater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Transactional
    public PhieuGiamGiaResponse create(PhieuGiamGiaRequest phieuGiamGiaRequest) {
        // Tạo mã phiếu giảm giá
        phieuGiamGiaRequest.setMaPhieuGiamGia(GenerateCodeRandomUtil.generateProductCode("PGG", 7));;
        // Đặt trạng thái phiếu giảm giá là ACTIVE
        phieuGiamGiaRequest.setTrangThai(StatusPhieuGiamGia.ACTIVE);
        // Luu va chuyen doi doi tuong thanh DTO
        return phieuGiamGiaResponseMapper.toDTO(phieuGiamGiaRepository.save(phieuGiamGiaRequestMapper.toEntity(phieuGiamGiaRequest)));
    }

    @Override
    @Transactional
    public PhieuGiamGiaResponse update(Long id, PhieuGiamGiaRequest phieuGiamGiaRequest) {
        PhieuGiamGia phieuGiamGiaExisting = phieuGiamGiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy với id: " + id));

        phieuGiamGiaExisting.setTenPhieuGiamGia(phieuGiamGiaRequest.getTenPhieuGiamGia());
        phieuGiamGiaExisting.setMoTa(phieuGiamGiaRequest.getMoTa());
        phieuGiamGiaExisting.setLoaiGiam(phieuGiamGiaRequest.getLoaiGiam());
        phieuGiamGiaExisting.setNgayBatDau(phieuGiamGiaRequest.getNgayBatDau());
        phieuGiamGiaExisting.setNgayKetThuc(phieuGiamGiaRequest.getNgayKetThuc());
        phieuGiamGiaExisting.setGiaTriGiamToiDa(phieuGiamGiaRequest.getGiaTriGiamToiDa());
        phieuGiamGiaExisting.setGiaTriGiam(phieuGiamGiaRequest.getGiaTriGiam());
        phieuGiamGiaExisting.setGiaTriGiamToiThieu(phieuGiamGiaRequest.getGiaTriGiamToiThieu());
        phieuGiamGiaExisting.setTrangThai(phieuGiamGiaRequest.getTrangThai());

        PhieuGiamGia phieuGiamGiaUpdated = phieuGiamGiaRepository.save(phieuGiamGiaExisting);
        return phieuGiamGiaResponseMapper.toDTO(phieuGiamGiaUpdated);
    }

    @Override
    public void delete(Long id) {

        PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Phiếu giảm giá không tồn tại"));
        phieuGiamGia.setTrangThai(StatusPhieuGiamGia.ACTIVE);
        phieuGiamGiaRepository.save(phieuGiamGia);
    }

    @Override
    public PhieuGiamGiaResponse getById(Long id) {
        PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy với id: " + id));
        return phieuGiamGiaResponseMapper.toDTO(phieuGiamGia);
    }

    public Page<PhieuGiamGiaResponse> searchPagePhieuGiamGia(Pageable pageable, PhieuGiamGiaSearchRequest phieuGiamGiaSearchRequest){
        Page<PhieuGiamGia> phieuGiamGiaPage =   phieuGiamGiaRepository.searchPagePhieuGiamGia(pageable,
                phieuGiamGiaSearchRequest.getMaPhieuGiamGia(),
                phieuGiamGiaSearchRequest.getTenPhieuGiamGia(),
                phieuGiamGiaSearchRequest.getNgayBatDau(),
                DateFormater.setEndDate(phieuGiamGiaSearchRequest.getNgayKetThuc()),
                phieuGiamGiaSearchRequest.getLoaiGiam(),
                phieuGiamGiaSearchRequest.getTrangThai());
        return phieuGiamGiaPage.map(phieuGiamGiaResponseMapper::toDTO);
    }

    public Map<String, Integer> getPhieuGiamGiaByStatus() {
        int activeCount = phieuGiamGiaRepository.countByStatus(StatusPhieuGiamGia.ACTIVE);
        int usedCount = phieuGiamGiaRepository.countByStatus(StatusPhieuGiamGia.USED);
        int expiredCount = phieuGiamGiaRepository.countByStatus(StatusPhieuGiamGia.EXPIRED);
        int cancelledCount = phieuGiamGiaRepository.countByStatus(StatusPhieuGiamGia.CANCELLED);

        Map<String, Integer> counts = new HashMap<>();
        counts.put("ACTIVE", activeCount);
        counts.put("USED", usedCount);
        counts.put("EXPIRED", expiredCount);
        counts.put("CANCELLED", cancelledCount);

        return counts;
    }
}
