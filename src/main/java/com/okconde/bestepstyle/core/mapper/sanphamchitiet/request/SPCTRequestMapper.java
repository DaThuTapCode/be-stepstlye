package com.okconde.bestepstyle.core.mapper.sanphamchitiet.request;

import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTRequest;
import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Trong Phu on 25/09/2024 22:18
 *
 * @author Trong Phu
 */
@Mapper(componentModel = "spring")
public interface SPCTRequestMapper extends IBaseMapper<SanPhamChiTiet, SPCTRequest> {
}
