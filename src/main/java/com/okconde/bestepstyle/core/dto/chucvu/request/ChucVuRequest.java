package com.okconde.bestepstyle.core.dto.chucvu.request;

import com.okconde.bestepstyle.core.dto.nhanvien.response.NhanVienShortResponse;
import com.okconde.bestepstyle.core.entity.NhanVien;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Quang Minh on 9/25/2024 21:55:05
 *
 * @author Quang Minh
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChucVuRequest {

    private Long idChucVu;

    private List<NhanVienShortResponse> nhanViens;

    private String tenChucVu;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;
}
