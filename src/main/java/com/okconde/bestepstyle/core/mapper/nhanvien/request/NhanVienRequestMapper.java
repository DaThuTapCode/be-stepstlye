package com.okconde.bestepstyle.core.mapper.nhanvien.request;

import com.okconde.bestepstyle.core.dto.nhanvien.request.NhanVienRequest;
import com.okconde.bestepstyle.core.entity.NhanVien;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created by Quang Minh on 9/25/2024 22:21:37
 *
 * @author Quang Minh
 */
@Mapper(componentModel = "spring")
public interface NhanVienRequestMapper extends IBaseMapper<NhanVien, NhanVienRequest> {
}
