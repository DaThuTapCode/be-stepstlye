package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Quang Minh on 9/23/2024 21:29:29
 *
 * @author Quang Minh
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "khach_hang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKhachHang;

    private String tenKhachHang;

    private String soDienThoai;

    private String email;

    private String ngaySinh;

    private Boolean gioiTinh;

    private String ghiChu;

    private LocalDateTime ngayChinhSua;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    @OneToMany(
            mappedBy = "khachHang",
            fetch = FetchType.LAZY
    )
    private List<DiaChiKhachHang> diaChiKhachHangs;

}
