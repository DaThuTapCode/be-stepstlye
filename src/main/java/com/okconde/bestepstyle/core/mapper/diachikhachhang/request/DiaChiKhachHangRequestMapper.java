package com.okconde.bestepstyle.core.mapper.diachikhachhang.request;

import com.okconde.bestepstyle.core.dto.diachikhachhang.request.DiaChiKhachHangRequest;
import com.okconde.bestepstyle.core.entity.DiaChiKhachHang;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Quang Minh on 9/25/2024 22:19:14
 *
 * @author Quang Minh
 */
@Mapper(componentModel = "spring")
public interface DiaChiKhachHangRequestMapper extends IBaseMapper<DiaChiKhachHang, DiaChiKhachHangRequest> {
}
