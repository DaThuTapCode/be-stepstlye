package com.okconde.bestepstyle.core.mapper.sanphamchitiet.response;

import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;
import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;


/**
 * Created by Trong Phu on 25/09/2024 22:19
 *
 * @author Trong Phu
 */

@Mapper(componentModel = "spring")

public interface SPCTResponseMapper extends IBaseMapper<SanPhamChiTiet, SPCTResponse> {
}
