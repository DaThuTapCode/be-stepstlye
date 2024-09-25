package com.okconde.bestepstyle.core.mapper.chucvu.response;

import com.okconde.bestepstyle.core.dto.chucvu.response.ChucVuResponse;
import com.okconde.bestepstyle.core.entity.ChucVu;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.mapstruct.Mapper;

/**
 * Created by Quang Minh on 9/25/2024 22:17:59
 *
 * @author Quang Minh
 */
@Mapper(componentModel = "spring")
public interface ChucVuResponseMapper extends IBaseMapper<ChucVu, ChucVuResponse> {

}
