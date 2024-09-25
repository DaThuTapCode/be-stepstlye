package com.okconde.bestepstyle.core.mapper.danhmuc.response;

import com.okconde.bestepstyle.core.dto.danhmuc.response.DanhMucResponse;
import com.okconde.bestepstyle.core.entity.DanhMuc;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Trong Phu on 25/09/2024 22:17
 *
 * @author Trong Phu
 */
@Mapper(componentModel = "spring")
public interface DanhMucResponseMapper extends IBaseMapper<DanhMuc, DanhMucResponse> {

}
