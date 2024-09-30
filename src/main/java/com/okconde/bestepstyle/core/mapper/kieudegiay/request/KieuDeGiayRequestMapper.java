package com.okconde.bestepstyle.core.mapper.kieudegiay.request;

import com.okconde.bestepstyle.core.dto.danhmuc.request.DanhMucRequest;
import com.okconde.bestepstyle.core.dto.kieudegiay.request.KieuDeGiayRequest;
import com.okconde.bestepstyle.core.entity.DanhMuc;
import com.okconde.bestepstyle.core.entity.KieuDeGiay;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Mapper(componentModel = "spring")
public interface KieuDeGiayRequestMapper extends IBaseMapper<KieuDeGiay, KieuDeGiayRequest> {
}
