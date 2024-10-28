package com.okconde.bestepstyle.feature.invoicemangerment.service;


import com.okconde.bestepstyle.core.dto.lichsuhoadon.request.LichSuHoaDonRequest;
import com.okconde.bestepstyle.core.dto.lichsuhoadon.request.LichSuHoaDonSearchRequest;
import com.okconde.bestepstyle.core.dto.lichsuhoadon.response.LichSuHoaDonResponse;
import com.okconde.bestepstyle.core.entity.LichSuHoaDon;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.lichsuhoadon.request.LichSuHoaDonRequestMapper;
import com.okconde.bestepstyle.core.mapper.lichsuhoadon.response.LichSuHoaDonResponseMapper;
import com.okconde.bestepstyle.core.repository.LichSuHoaDonRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by TuanIf on 9/25/2024 20:28:15
 *
 * @author TuanIf
 */

@Service(value = "LichSuHoaDonService")

public class LichSuHoaDonService implements IBaseService<LichSuHoaDon, Long, LichSuHoaDonRequest, LichSuHoaDonResponse> {
    //Repo
    private final LichSuHoaDonRepository lichSuHoaDonRepository;

    //Mapper
    private final LichSuHoaDonResponseMapper lichSuHoaDonResponseMapper;

    private final LichSuHoaDonRequestMapper lichSuHoaDonRequestMapper;

    public LichSuHoaDonService(LichSuHoaDonResponseMapper lichSuHoaDonResponseMapper, LichSuHoaDonRepository lichSuHoaDonRepository, LichSuHoaDonRequestMapper lichSuHoaDonRequestMapper) {
        this.lichSuHoaDonResponseMapper = lichSuHoaDonResponseMapper;
        this.lichSuHoaDonRepository = lichSuHoaDonRepository;
        this.lichSuHoaDonRequestMapper = lichSuHoaDonRequestMapper;
    }

    @Override
    public List<LichSuHoaDonResponse> getPage(Pageable pageable) {
        Page<LichSuHoaDon> page = lichSuHoaDonRepository.findAll(pageable);
        return page.map(lichSuHoaDonResponseMapper::toDTO).getContent();
    }

    @Override
    public List<LichSuHoaDonResponse> getAll() {
        List<LichSuHoaDon> lichSuHoaDonList = lichSuHoaDonRepository.findAll();
        return lichSuHoaDonResponseMapper.listToDTO(lichSuHoaDonList);
    }

    @Override
    @Transactional
    public LichSuHoaDonResponse create(LichSuHoaDonRequest lichSuHoaDonRequest) {

        lichSuHoaDonRequest.setMaLichSuHoaDon(GenerateCodeRandomUtil.generateProductCode("LSHD",6));

        LichSuHoaDon lichSuHoaDonNew = lichSuHoaDonRequestMapper.toEntity(lichSuHoaDonRequest);
        lichSuHoaDonNew.setHanhDong(lichSuHoaDonNew.getHanhDong());
        lichSuHoaDonNew.setNgayTao(LocalDateTime.now());
        lichSuHoaDonNew.setNguoiThucHien(lichSuHoaDonNew.getNguoiThucHien());
        lichSuHoaDonNew.setTrangThai(StatusEnum.ACTIVE);

        LichSuHoaDon lichSuHoaDonSaved = lichSuHoaDonRepository.save(lichSuHoaDonNew);
        return lichSuHoaDonResponseMapper.toDTO(lichSuHoaDonSaved);
    }

    @Override
    @Transactional
    public LichSuHoaDonResponse update(Long id, LichSuHoaDonRequest lichSuHoaDonRequest) {
        LichSuHoaDon lichSuHoaDonExisting = lichSuHoaDonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy với id: " + id));

        lichSuHoaDonExisting.setHanhDong(lichSuHoaDonRequest.getHanhDong());
        lichSuHoaDonExisting.setNgayTao(LocalDateTime.now());
        lichSuHoaDonExisting.setNguoiThucHien(lichSuHoaDonRequest.getNguoiThucHien());
        lichSuHoaDonExisting.setTrangThai(lichSuHoaDonRequest.getTrangThai());

        LichSuHoaDon lichSuHoaDonUpdated = lichSuHoaDonRepository.save(lichSuHoaDonExisting);
        return lichSuHoaDonResponseMapper.toDTO(lichSuHoaDonUpdated);
    }

    @Override
    public void delete(Long id) {
        LichSuHoaDon lichSuHoaDon = lichSuHoaDonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lịch sử hóa đơn không tồn tại"));
        lichSuHoaDon.setTrangThai(StatusEnum.ACTIVE);
        lichSuHoaDonRepository.save(lichSuHoaDon);
    }

    @Override
    public LichSuHoaDonResponse getById(Long id) {
        LichSuHoaDon lichSuHoaDon = lichSuHoaDonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy với id: " + id));
        return lichSuHoaDonResponseMapper.toDTO(lichSuHoaDon);
    }

    public Page<LichSuHoaDonResponse> searchPageLichSuHoaDon(Pageable pageable, LichSuHoaDonSearchRequest lichSuHoaDonSearchRequest){
        Page<LichSuHoaDon> lichSuHoaDonPage = lichSuHoaDonRepository.searchPageLichSuHoaDon(pageable, lichSuHoaDonSearchRequest.getMaLichSuHoaDon());
        return lichSuHoaDonPage.map(lichSuHoaDonResponseMapper::toDTO);
    }
}
