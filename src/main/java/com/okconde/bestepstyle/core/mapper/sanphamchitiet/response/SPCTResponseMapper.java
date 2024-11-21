package com.okconde.bestepstyle.core.mapper.sanphamchitiet.response;

import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;
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
public interface SPCTResponseMapper extends IBaseMapper<SanPhamChiTiet, SPCTResponse> {

    @Override
    @Mapping(target = "anh", source = "anh", qualifiedByName = "anh")
    SPCTResponse toDTO(SanPhamChiTiet sanPhamChiTiet);

    @Override
    List<SPCTResponse> listToDTO(List<SanPhamChiTiet> listE);

    @Named(value = "anh")
    default String mapServerForImage(String anh) {
        if(anh != null){
            return VariableHehe.SERVER_PORT + "/images/" + anh;
        }
        return null;
    }
}
