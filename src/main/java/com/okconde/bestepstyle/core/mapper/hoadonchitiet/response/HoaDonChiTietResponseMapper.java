package com.okconde.bestepstyle.core.mapper.hoadonchitiet.response;

import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.entity.HoaDonChiTiet;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by TuanIf on 9/25/2024 22:18:37
 *
 * @author TuanIf
 */

@Mapper(componentModel = "spring")

public interface HoaDonChiTietResponseMapper extends IBaseMapper<HoaDonChiTiet, HoaDonChiTietResponse> {

}
