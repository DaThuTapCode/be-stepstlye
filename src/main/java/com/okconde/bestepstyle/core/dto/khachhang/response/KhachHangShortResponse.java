package com.okconde.bestepstyle.core.dto.khachhang.response;

<<<<<<< HEAD
import com.okconde.bestepstyle.core.dto.diachikhachhang.response.DiaChiKhachHangShortResponse;
import com.okconde.bestepstyle.core.entity.DiaChiKhachHang;
=======
import com.fasterxml.jackson.annotation.JsonFormat;
>>>>>>> 58b26b38649e648c38c169a33050bee434223582
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
import java.util.List;
=======
import java.time.LocalDate;
import java.time.LocalDateTime;
>>>>>>> 58b26b38649e648c38c169a33050bee434223582

/**
 * Created by Quang Minh on 9/25/2024 21:33:47
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KhachHangShortResponse {

    private Long idKhachHang;

    private String tenKhachHang;

    private String soDienThoai;

    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngaySinh;

    private Boolean gioiTinh;

    private String ghiChu;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayTao;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayChinhSua;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
