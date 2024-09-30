package com.okconde.bestepstyle.core.mapper.hoadonchitiet.request;

import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.entity.HoaDonChiTiet;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by TuanIf on 9/25/2024 22:18:25
 *
 * @author TuanIf
 */

@Mapper(componentModel = "spring")

public interface HoaDonChiTietRequestMapper extends IBaseMapper<HoaDonChiTiet, HoaDonChiTietRequest> {

}
