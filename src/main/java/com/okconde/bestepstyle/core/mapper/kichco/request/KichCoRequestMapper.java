package com.okconde.bestepstyle.core.mapper.kichco.request;

import com.okconde.bestepstyle.core.dto.kichco.request.KichCoRequest;
import com.okconde.bestepstyle.core.entity.KichCo;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Mapper(componentModel = "spring")
public interface KichCoRequestMapper extends IBaseMapper<KichCo, KichCoRequest> {
}
