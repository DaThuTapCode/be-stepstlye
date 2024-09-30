package com.okconde.bestepstyle.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Quang Minh on 9/23/2024 21:29:50
 *
 * @author Quang Minh
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nhan_vien")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNhanVien;

    private String hoTen;

    private String ngaySinh;

    private String diaChi;

    private Boolean gioiTinh;

    private String soDienThoai;

    private String email;

    private String ghiChu;

    private String anh;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_chuc_vu")
    private ChucVu chucVu;
}
