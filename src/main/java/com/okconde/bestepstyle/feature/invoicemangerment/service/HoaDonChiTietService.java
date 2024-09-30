package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.entity.HoaDonChiTiet;
import com.okconde.bestepstyle.core.mapper.hoadonchitiet.request.HoaDonChiTietRequestMapper;
import com.okconde.bestepstyle.core.mapper.hoadonchitiet.response.HoaDonChiTietResponseMapper;
import com.okconde.bestepstyle.core.repository.HoaDonChiTietRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by TuanIf on 9/25/2024 20:28:02
 *
 * @author TuanIf
 */

@Service(value = "HoaDonChiTietService")

public class HoaDonChiTietService implements IBaseService<HoaDonChiTiet, Long, HoaDonChiTietRequest, HoaDonChiTietResponse> {

    private final HoaDonChiTietResponseMapper hoaDonChiTietResponseMapper;

    private final HoaDonChiTietRepository hoaDonChiTietRepository;

    public HoaDonChiTietService(HoaDonChiTietResponseMapper hoaDonChiTietResponseMapper, HoaDonChiTietRepository hoaDonChiTietRepository) {
        this.hoaDonChiTietResponseMapper = hoaDonChiTietResponseMapper;
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
    }

    @Override
    public List<HoaDonChiTietResponse> getPage(Pageable pageable) {
        Page<HoaDonChiTiet> page = hoaDonChiTietRepository.findAll(pageable);
        return page.map(hoaDonChiTietResponseMapper:: toDTO).getContent();
    }

    @Override
    public List<HoaDonChiTietResponse> getAll() {
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietRepository.findAll();
        return hoaDonChiTietResponseMapper.listToDTO(hoaDonChiTietList);
    }

    @Override
    public HoaDonChiTietResponse create(HoaDonChiTietRequest hoaDonChiTietRequest) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietResponseMapper.toEntity(hoaDonChiTietRequest);
        hoaDonChiTiet = hoaDonChiTietRepository.save(hoaDonChiTiet);
        return hoaDonChiTietResponseMapper.toDTO(hoaDonChiTiet);
    }

    @Override
    public HoaDonChiTietResponse update(Long id, HoaDonChiTietRequest hoaDonChiTietRequest) {
        Optional<HoaDonChiTiet> optionalHoaDonChiTiet = hoaDonChiTietRepository.findById(id);
        if(optionalHoaDonChiTiet.isPresent()) {
            HoaDonChiTiet hoaDonChiTiet = optionalHoaDonChiTiet.get();
            // Update các trường từ Resquet
            hoaDonChiTiet = hoaDonChiTietResponseMapper.toEntity(hoaDonChiTietRequest);
            hoaDonChiTiet.setIdHoaDonChiTiet(id);
            hoaDonChiTiet = hoaDonChiTietRepository.save(hoaDonChiTiet);
            return hoaDonChiTietResponseMapper.toDTO(hoaDonChiTiet);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id" + id);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<HoaDonChiTiet> optionalHoaDonChiTiet = hoaDonChiTietRepository.findById(id);
        if (optionalHoaDonChiTiet.isPresent()){
            HoaDonChiTiet hoaDonChiTiet = optionalHoaDonChiTiet.get();
            hoaDonChiTiet.setDeleted(true);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id: " + id);
        }
    }

    @Override
    public HoaDonChiTietResponse getById(Long aLong) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(aLong)
                .orElseThrow(() -> new )
    }
}
