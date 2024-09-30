package com.okconde.bestepstyle.core.mapper.hoadon.response;

import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by TuanIf on 9/25/2024 22:18:00
 *
 * @author TuanIf
 */

@Mapper(componentModel = "spring")

public interface HoaDonResponseMapper extends IBaseMapper<HoaDon, HoaDonResponse> {
}
