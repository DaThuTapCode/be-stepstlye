package com.okconde.bestepstyle.core.mapper.khachhang.response;

import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangResponse;
import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Quang Minh on 9/25/2024 22:20:57
 *
 * @author Quang Minh
 */
@Mapper(componentModel = "spring")
public interface KhachHangResponseMapper extends IBaseMapper<KhachHang, KhachHangResponse> {
}
