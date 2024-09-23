package com.okconde.bestepstyle.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by TuanIf on 9/23/2024 21:30:19
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHoaDon;
    private LocalDateTime ngayTaoDon;
    private BigDecimal phiVanChuyen;
    private BigDecimal tongTien;
    private BigDecimal tongTienSauGiam;
    private LocalDateTime ngayChinhSua;
    private LocalDateTime ngayXacNhan;
    private LocalDateTime ngayNhanHang;
    private String loaiHoaDon;
    private String tenKhachHang;
    private String diaChiGiaoHang;
    private String soDienThoaiKhachHang;
    private String ghiChu;


    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "id_thanh_toan")
    private ThanhToan thanhToan;

    @ManyToOne
    @JoinColumn(name = "id_phieu_giam_gia")
    private PhieuGiamGia phieuGiamGia;
}
