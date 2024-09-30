package com.okconde.bestepstyle.core.mapper.hoadon.request;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by TuanIf on 9/25/2024 22:17:32
 *
 * @author TuanIf
 */

@Mapper(componentModel = "spring")

public interface HoaDonRequestMapper extends IBaseMapper<HoaDon, HoaDonRequest> {
}
