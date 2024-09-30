package com.okconde.bestepstyle.core.mapper.thuonghieu.request;

import com.okconde.bestepstyle.core.dto.thuonghieu.request.ThuongHieuRequest;
import com.okconde.bestepstyle.core.entity.ThuongHieu;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Trong Phu on 25/09/2024 22:19
 *
 * @author Trong Phu
 */
@Mapper(componentModel = "spring")
public interface ThuongHieuRequestMapper extends IBaseMapper<ThuongHieu, ThuongHieuRequest> {
}
