package com.okconde.bestepstyle.core.mapper.kieudegiay.response;

import com.okconde.bestepstyle.core.dto.kieudegiay.reponse.KieuDeGiayResponse;
import com.okconde.bestepstyle.core.entity.KieuDeGiay;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Mapper(componentModel = "spring")
public interface KieuDeGiayResponseMapper extends IBaseMapper<KieuDeGiay, KieuDeGiayResponse> {
}
