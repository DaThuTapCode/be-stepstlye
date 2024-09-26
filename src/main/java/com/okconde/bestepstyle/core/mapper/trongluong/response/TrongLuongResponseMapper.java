package com.okconde.bestepstyle.core.mapper.trongluong.response;

import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
import com.okconde.bestepstyle.core.entity.TrongLuong;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Mapper(componentModel = "spring")
public interface TrongLuongResponseMapper extends IBaseMapper<TrongLuong, TrongLuongResponse> {
}
