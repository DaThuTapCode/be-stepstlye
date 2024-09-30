package com.okconde.bestepstyle.core.mapper.lichsuhoadon.request;

import com.okconde.bestepstyle.core.dto.lichsuhoadon.request.LichSuHoaDonRequest;
import com.okconde.bestepstyle.core.entity.LichSuHoaDon;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by TuanIf on 9/25/2024 22:19:18
 *
 * @author TuanIf
 */

@Mapper(componentModel = "spring")

public interface LichSuHoaDonRequestMapper extends IBaseMapper<LichSuHoaDon, LichSuHoaDonRequest> {
}
