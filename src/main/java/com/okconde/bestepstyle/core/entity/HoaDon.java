package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.until.enumutil.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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

    git

    @OneToMany(mappedBy = "khach_hang",
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    @OneToMany(mappedBy = "nhan_vien",
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @OneToOne(mappedBy = "id_thanh_toan")
    @JoinColumn(name = "id_thanh_toan")
    private ThanhToan thanhToan;

    @OneToOne(mappedBy = "id_phieu_giam_gia")
    @JoinColumn(name = "id_phieu_giam_gia")
    private PhieuGiamGia phieuGiamGia;
}
