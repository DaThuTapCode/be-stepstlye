package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.util.enumutil.StatusSP;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Trong Phu on 23/09/2024 21:28
 *
 * @author Trong Phu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSanPham;

    private String maSanPham;

    private String tenSanPham;

    private String moTa;

    private LocalDateTime ngayTao;

    private LocalDateTime ngayChinhSua;

    private String nguoiTao;

    @Enumerated(EnumType.STRING)
    private StatusSP trangThai;

    @ManyToOne
    @JoinColumn(name = "id_danh_muc")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "id_thuong_hieu")
    private ThuongHieu thuongHieu;

    @ManyToOne
    @JoinColumn(name = "id_chat_lieu")
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "id_trong_luong")
    private TrongLuong trongLuong;

    @OneToMany(mappedBy = "sanPham")
    private List<SanPhamChiTiet> sanPhamChiTiets;

}
