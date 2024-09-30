package com.okconde.bestepstyle.core.mapper.diachikhachhang.response;

import com.okconde.bestepstyle.core.dto.diachikhachhang.response.DiaChiKhachHangResponse;
import com.okconde.bestepstyle.core.entity.DiaChiKhachHang;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Quang Minh on 9/25/2024 22:19:34
 *
 * @author Quang Minh
 */
@Mapper(componentModel = "spring")
public interface DiaChiKhachHangResponseMapper extends IBaseMapper<DiaChiKhachHang, DiaChiKhachHangResponse> {
}
