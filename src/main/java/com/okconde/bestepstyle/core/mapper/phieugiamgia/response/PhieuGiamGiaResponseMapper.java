package com.okconde.bestepstyle.core.mapper.phieugiamgia.response;

import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
import com.okconde.bestepstyle.core.entity.PhieuGiamGia;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by TuanIf on 9/25/2024 22:20:21
 *
 * @author TuanIf
 */

@Mapper(componentModel = "spring")

public interface PhieuGiamGiaResponseMapper extends IBaseMapper<PhieuGiamGia, PhieuGiamGiaResponse> {
}
