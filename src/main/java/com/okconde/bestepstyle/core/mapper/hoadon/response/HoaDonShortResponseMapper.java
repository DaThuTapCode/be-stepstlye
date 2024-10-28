package com.okconde.bestepstyle.core.mapper.hoadon.response;

import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by TuanIf on 9/25/2024 22:18:12
 *
 * @author TuanIf
 */
@Mapper(componentModel = "spring")
public interface HoaDonShortResponseMapper extends IBaseMapper<HoaDon, HoaDonShortResponse> {
}
