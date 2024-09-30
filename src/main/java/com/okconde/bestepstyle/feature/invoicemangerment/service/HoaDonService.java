package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.entity.HoaDonChiTiet;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonResponseMapper;
import com.okconde.bestepstyle.core.repository.HoaDonRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by TuanIf on 9/25/2024 20:27:52
 *
 * @author TuanIf
 */

@Service(value = "HoaDonService")

public class HoaDonService implements IBaseService<HoaDon, Long, HoaDonRequest, HoaDonResponse> {

    private final HoaDonResponseMapper hoaDonResponseMapper;

    private final HoaDonRepository hoaDonRepository;

    public HoaDonService(HoaDonResponseMapper hoaDonResponseMapper, HoaDonRepository hoaDonRepository) {
        this.hoaDonResponseMapper = hoaDonResponseMapper;
        this.hoaDonRepository = hoaDonRepository;
    }


    @Override
    public List<HoaDonResponse> getPage(Pageable pageable) {
        Page<HoaDon> page = hoaDonRepository.findAll(pageable);
        return page.map(hoaDonResponseMapper::toDTO).getContent();
    }

    @Override
    public List<HoaDonResponse> getAll() {
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        return hoaDonResponseMapper.listToDTO(hoaDonList);
    }

    @Override
    public HoaDonResponse create(HoaDonRequest hoaDonRequest) {
        HoaDon hoaDon = hoaDonResponseMapper.toEntity(hoaDonRequest);
        hoaDon = hoaDonRepository.save(hoaDon);
        return hoaDonResponseMapper.toDTO(hoaDon);
    }

    @Override
    public HoaDonResponse update(Long id, HoaDonRequest hoaDonRequest) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(id);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            // Update các trường từ Resquet
            hoaDon = hoaDonResponseMapper.toEntity(hoaDonRequest);
            hoaDon.setIdHoaDon(id);
            hoaDon = hoaDonRepository.save(hoaDon);
            return hoaDonResponseMapper.toDTO(hoaDon);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id" + id);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(id);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            hoaDon.setDeleted(true);
            hoaDonRepository.save(hoaDon);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id: " + id);
        }
    }

    @Override
    public HoaDonResponse getById(Long id) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(id);
        if (optionalHoaDon.isPresent() && optionalHoaDon.get().isDeleted()) {
            return hoaDonResponseMapper.toDTO(optionalHoaDon.get());
        } else {
            throw new EntityNotFoundException("Không tìm thấy id: " + id);
        }
    }
}
