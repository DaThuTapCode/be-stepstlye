package com.okconde.bestepstyle.core.mapper.sanphamchitiet.response;


import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTShortResponse;
import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import com.okconde.bestepstyle.core.util.variabletp.VariableHehe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 22:19
 *
 * @author Trong Phu
 */
@Mapper(componentModel = "spring")
public interface SPCTShortResponseMapper extends IBaseMapper<SanPhamChiTiet, SPCTShortResponse> {
    @Override
    @Mapping(target = "anh", source = "anh", qualifiedByName = "anh")
    SPCTShortResponse toDTO(SanPhamChiTiet sanPhamChiTiet);

    @Override
    List<SPCTShortResponse> listToDTO(List<SanPhamChiTiet> listE);


    @Named(value = "anh")
    default String mapServerForImage(String anh) {
        if(anh != null){
            return VariableHehe.SERVER_PORT + "/images/" + anh;
        }
        return null;
    }
}
