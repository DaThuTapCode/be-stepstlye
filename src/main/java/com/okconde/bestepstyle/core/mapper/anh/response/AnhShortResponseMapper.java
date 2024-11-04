package com.okconde.bestepstyle.core.mapper.anh.response;

import com.okconde.bestepstyle.core.dto.anh.response.AnhShortResponse;
import com.okconde.bestepstyle.core.entity.Anh;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import com.okconde.bestepstyle.core.util.variabletp.VariableHehe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * Created by Trong Phu on 02/11/2024 20:27
 *
 * @author Trong Phu
 */
@Mapper(componentModel = "spring")
public interface AnhShortResponseMapper extends IBaseMapper<Anh, AnhShortResponse> {

    @Override
    List<AnhShortResponse> listToDTO(List<Anh> listE);

    @Override
    @Mapping(source = "tenAnh", target = "tenAnh", qualifiedByName = "tenAnh")
    AnhShortResponse toDTO(Anh anh);

    @Named(value = "tenAnh")
    default String toUrlAnh(String tenAnh) {
        if(tenAnh != null && !tenAnh.isEmpty()){
            return VariableHehe.SERVER_PORT + "/images/" + tenAnh;
        }
        return null;
    }
}
