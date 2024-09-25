package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.entity.HoaDonChiTiet;
import com.okconde.bestepstyle.core.mapper.hoadonchitiet.request.HoaDonChiTietRequestMapper;
import com.okconde.bestepstyle.core.mapper.hoadonchitiet.response.HoaDonChiTietResponseMapper;
import com.okconde.bestepstyle.core.repository.HoaDonChiTietRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return null;
    }

    @Override
    public List<HoaDonChiTietResponse> getAll() {
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietRepository.findAll();
        return hoaDonChiTietResponseMapper.listToDTO(hoaDonChiTietList);
    }

    @Override
    public HoaDonChiTietResponse create(HoaDonChiTietRequest hoaDonChiTietRequest) {
        return null;
    }

    @Override
    public HoaDonChiTietResponse update(Long aLong, HoaDonChiTietRequest hoaDonChiTietRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public HoaDonChiTietResponse getById(Long aLong) {
        return null;
    }
}
