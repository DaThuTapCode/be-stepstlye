package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.entity.HoaDonChiTiet;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.hoadonchitiet.request.HoaDonChiTietRequestMapper;
import com.okconde.bestepstyle.core.mapper.hoadonchitiet.response.HoaDonChiTietResponseMapper;
import com.okconde.bestepstyle.core.repository.HoaDonChiTietRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDonChiTiet;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by TuanIf on 9/25/2024 20:28:02
 *
 * @author TuanIf
 */

@Service(value = "HoaDonChiTietService")

public class HoaDonChiTietService implements IBaseService<HoaDonChiTiet, Long, HoaDonChiTietRequest, HoaDonChiTietResponse> {
    //Repo
    private final HoaDonChiTietRepository hoaDonChiTietRepository;

    //Mapper
    private final HoaDonChiTietResponseMapper hoaDonChiTietResponseMapper;
    private final HoaDonChiTietRequestMapper hoaDonChiTietRequestMapper;

    public HoaDonChiTietService(HoaDonChiTietResponseMapper hoaDonChiTietResponseMapper, HoaDonChiTietRepository hoaDonChiTietRepository, HoaDonChiTietRequestMapper hoaDonChiTietRequestMapper) {
        this.hoaDonChiTietResponseMapper = hoaDonChiTietResponseMapper;
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
        this.hoaDonChiTietRequestMapper = hoaDonChiTietRequestMapper;
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
    @Transactional
    public HoaDonChiTietResponse create(HoaDonChiTietRequest hoaDonChiTietRequest) {
        HoaDonChiTiet hoaDonChiTietNew = hoaDonChiTietRequestMapper.toEntity(hoaDonChiTietRequest);
        hoaDonChiTietNew.setSoLuong(hoaDonChiTietNew.getSoLuong());
        hoaDonChiTietNew.setDonGia(hoaDonChiTietNew.getDonGia());
        hoaDonChiTietNew.setTongTien(hoaDonChiTietNew.getTongTien());
        hoaDonChiTietNew.setTrangThai(StatusHoaDonChiTiet.ACTIVE);
        HoaDonChiTiet hoaDonChiTietSaved = hoaDonChiTietRepository.save(hoaDonChiTietNew);
        return hoaDonChiTietResponseMapper.toDTO(hoaDonChiTietSaved);
    }

    @Override
    @Transactional
    public HoaDonChiTietResponse update(Long id, HoaDonChiTietRequest hoaDonChiTietRequest) {
        HoaDonChiTiet hoaDonChiTietExisting = hoaDonChiTietRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy HDCT với id: " + id));

        HoaDonChiTiet hoaDonChiTietUpdate = hoaDonChiTietRequestMapper.toEntity(hoaDonChiTietRequest);
        hoaDonChiTietExisting.setSoLuong(hoaDonChiTietUpdate.getSoLuong());
        hoaDonChiTietExisting.setDonGia(hoaDonChiTietUpdate.getDonGia());
        hoaDonChiTietExisting.setTongTien(hoaDonChiTietUpdate.getTongTien());

        HoaDonChiTiet hoaDonChiTietUpdated = hoaDonChiTietRepository.save(hoaDonChiTietExisting);
        return hoaDonChiTietResponseMapper.toDTO(hoaDonChiTietUpdated);
    }

    @Override
    public void delete(Long id) {
        Optional<HoaDonChiTiet> optionalHoaDonChiTiet = hoaDonChiTietRepository.findById(id);
        if (optionalHoaDonChiTiet.isPresent()){
            HoaDonChiTiet hoaDonChiTiet = optionalHoaDonChiTiet.get();
            hoaDonChiTiet.setTrangThai(StatusHoaDonChiTiet.INACTIVE);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        } else {
            throw new ResourceNotFoundException("Không tìm thấy id: " + id);
        }
    }

    @Override
    public HoaDonChiTietResponse getById(Long id) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy HDCT với id: " + id));
        return hoaDonChiTietResponseMapper.toDTO(hoaDonChiTiet);
    }

    public List<HoaDonChiTietResponse> getByIdHoaDon(Long id) {
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository.findHoaDonChiTietByIdHoaDon(id);
        return hoaDonChiTietResponseMapper.listToDTO(hoaDonChiTiet);
    }
}
