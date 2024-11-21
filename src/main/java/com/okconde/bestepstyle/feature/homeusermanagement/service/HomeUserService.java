package com.okconde.bestepstyle.feature.homeusermanagement.service;

import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonResponseMapper;
import com.okconde.bestepstyle.core.repository.HoaDonRepository;
import com.okconde.bestepstyle.core.repository.KhachHangRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Trong Phu on 21/11/2024 00:15
 *
 * @author Trong Phu
 */
@Service
public class HomeUserService {

    private final KhachHangRepository khachHangRepository;
    private final HoaDonRepository hoaDonRepository;

    private final HoaDonResponseMapper hoaDonResponseMapper;

    public HomeUserService(KhachHangRepository khachHangRepository, HoaDonRepository hoaDonRepository, HoaDonResponseMapper hoaDonResponseMapper) {
        this.khachHangRepository = khachHangRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.hoaDonResponseMapper = hoaDonResponseMapper;
    }

    public List<HoaDonResponse> getHoaDonTheoKhachHang(String maKH) {
        List<HoaDon> listHDByKH = hoaDonRepository.getListHoaDonByKhachHang(maKH);
        return hoaDonResponseMapper.listToDTO(listHDByKH);
    }

}
