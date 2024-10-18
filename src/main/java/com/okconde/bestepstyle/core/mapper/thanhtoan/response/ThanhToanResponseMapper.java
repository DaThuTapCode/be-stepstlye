package com.okconde.bestepstyle.core.mapper.thanhtoan.response;

import com.okconde.bestepstyle.core.dto.thanhtoan.response.ThanhToanResponse;
import com.okconde.bestepstyle.core.entity.ThanhToan;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by TuanIf on 10/16/2024 16:40:41
 *
 * @author TuanIf
 */

@Mapper(componentModel = "spring")

public interface ThanhToanResponseMapper extends IBaseMapper<ThanhToan, ThanhToanResponse> {
}
