package com.okconde.bestepstyle.core.mapper.lichsuhoadon.response;

import com.okconde.bestepstyle.core.dto.lichsuhoadon.response.LichSuHoaDonResponse;
import com.okconde.bestepstyle.core.entity.LichSuHoaDon;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by TuanIf on 9/25/2024 22:19:34
 *
 * @author TuanIf
 */

@Mapper(componentModel = "spring")

public interface LichSuHoaDonResponseMapper extends IBaseMapper<LichSuHoaDon, LichSuHoaDonResponse> {
}
