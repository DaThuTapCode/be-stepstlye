package com.okconde.bestepstyle.core.mapper.sanpham.request;

import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamRequest;
import com.okconde.bestepstyle.core.entity.SanPham;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Trong Phu on 25/09/2024 22:17
 *
 * @author Trong Phu
 */
@Mapper(componentModel = "spring")
public interface SanPhamRequestMapper extends IBaseMapper<SanPham, SanPhamRequest> {
}
