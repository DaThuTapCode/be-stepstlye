package com.okconde.bestepstyle.core.mapper.phieugiamgia.request;

import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaRequest;
import com.okconde.bestepstyle.core.entity.PhieuGiamGia;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by TuanIf on 9/25/2024 22:19:50
 *
 * @author TuanIf
 */

@Mapper(componentModel = "spring")

public interface PhieuGiamGiaRequestMapper extends IBaseMapper<PhieuGiamGia, PhieuGiamGiaRequest> {
}
