package com.okconde.bestepstyle.core.mapper.khachhang.request;

import com.okconde.bestepstyle.core.dto.khachhang.request.KhachHangRequest;
import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Quang Minh on 9/25/2024 22:20:42
 *
 * @author Quang Minh
 */
@Mapper(componentModel = "spring")
public interface KhachHangRequestMapper extends IBaseMapper<KhachHang, KhachHangRequest> {
}
