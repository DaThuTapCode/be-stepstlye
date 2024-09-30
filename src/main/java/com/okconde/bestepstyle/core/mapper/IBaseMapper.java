package com.okconde.bestepstyle.core.mapper;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.lichsuhoadon.request.LichSuHoaDonRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaRequest;

import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 19:00
 *
 * @author Trong Phu
 */

public interface IBaseMapper <E, D>{
    /**
     * Chuyển đổi Entity sang DTO
     * @param e Entity cần chuyển đổi
     * */
    D toDTO(E e);

    /**
     * Chuyển đổi danh sách Entity sang danh sách DTO
     * @param listE danh sách Entity cần chuyển đổi
     * */
    List<D> listToDTO(List<E> listE);

    /**
     * Chuyển đổi DTO sang Entity
     * @param d DTO cần chuyển đổi
     * */
    E toEntity(HoaDonChiTietRequest d);
    E toEntity(HoaDonRequest d);
    E toEntity(LichSuHoaDonRequest d);
    E toEntity(PhieuGiamGiaRequest d);

    /**
     * Chuyển đổi danh sách DTO sang danh sách Entity
     * @param d Danh sách DTO cần chuyển đổi
     * */
    List<E> toListEntity(List<D> d);
}
