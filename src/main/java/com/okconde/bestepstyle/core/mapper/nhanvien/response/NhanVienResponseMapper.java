package com.okconde.bestepstyle.core.mapper.nhanvien.response;

import com.okconde.bestepstyle.core.dto.nhanvien.response.NhanVienResponse;
import com.okconde.bestepstyle.core.entity.NhanVien;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Quang Minh on 9/25/2024 22:21:52
 *
 * @author Quang Minh
 */
@Mapper(componentModel = "spring")
public interface NhanVienResponseMapper extends IBaseMapper<NhanVien, NhanVienResponse> {
}
