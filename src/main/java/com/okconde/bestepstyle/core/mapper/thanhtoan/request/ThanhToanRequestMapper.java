package com.okconde.bestepstyle.core.mapper.thanhtoan.request;

import com.okconde.bestepstyle.core.dto.thanhtoan.request.ThanhToanRequest;
import com.okconde.bestepstyle.core.entity.ThanhToan;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by TuanIf on 10/16/2024 16:40:30
 *
 * @author TuanIf
 */

@Mapper(componentModel = "spring")

public interface ThanhToanRequestMapper extends IBaseMapper<ThanhToan, ThanhToanRequest> {
}
