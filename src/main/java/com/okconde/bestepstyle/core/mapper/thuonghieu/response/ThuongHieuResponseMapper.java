package com.okconde.bestepstyle.core.mapper.thuonghieu.response;

import com.okconde.bestepstyle.core.dto.thuonghieu.response.ThuongHieuResponse;
import com.okconde.bestepstyle.core.entity.ThuongHieu;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Trong Phu on 25/09/2024 22:20
 *
 * @author Trong Phu
 */
@Mapper(componentModel = "spring")
public interface ThuongHieuResponseMapper extends IBaseMapper<ThuongHieu, ThuongHieuResponse> {
}
