package com.okconde.bestepstyle.core.mapper.sanpham.response;

import com.okconde.bestepstyle.core.dto.sanpham.response.SanPhamResponse;
import com.okconde.bestepstyle.core.entity.SanPham;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Trong Phu on 25/09/2024 22:18
 *
 * @author Trong Phu
 */
@Mapper(componentModel = "spring")
public interface SanPhamResponseMapper extends IBaseMapper<SanPham, SanPhamResponse> {
}
