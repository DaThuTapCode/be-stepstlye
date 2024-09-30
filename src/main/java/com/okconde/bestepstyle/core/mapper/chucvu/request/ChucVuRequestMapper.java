package com.okconde.bestepstyle.core.mapper.chucvu.request;

import com.okconde.bestepstyle.core.dto.chucvu.request.ChucVuRequest;
import com.okconde.bestepstyle.core.entity.ChucVu;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Quang Minh on 9/25/2024 22:17:27
 *
 * @author Quang Minh
 */
@Mapper(componentModel = "spring")
public interface ChucVuRequestMapper extends IBaseMapper<ChucVu, ChucVuRequest> {
}
