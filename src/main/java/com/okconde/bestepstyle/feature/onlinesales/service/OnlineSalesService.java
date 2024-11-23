package com.okconde.bestepstyle.feature.onlinesales.service;

import com.okconde.bestepstyle.core.config.auth.AuthenticationUtil;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonBanOnlineRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietBanOnlineRequest;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTRequest;
import com.okconde.bestepstyle.core.entity.*;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.mapper.hoadon.request.HoaDonRequestMapper;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonResponseMapper;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonShortResponseMapper;
import com.okconde.bestepstyle.core.mapper.hoadonchitiet.response.HoaDonChiTietResponseMapper;
import com.okconde.bestepstyle.core.mapper.khachhang.response.KhachHangResponseMapper;
import com.okconde.bestepstyle.core.mapper.phieugiamgia.response.PhieuGiamGiaResponseMapper;
import com.okconde.bestepstyle.core.mapper.sanpham.response.SanPhamShortResponseMapper;
import com.okconde.bestepstyle.core.mapper.sanphamchitiet.response.SPCTResponseMapper;
import com.okconde.bestepstyle.core.repository.*;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Trong Phu on 19/11/2024 20:59
 *
 * @author Trong Phu
 */
@Service
public class OnlineSalesService implements IOnlineSalesService{

    //  Repository
    private final HoaDonRepository hoaDonRepository;
    private final SanPhamRepository sanPhamRepository;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    private final PhieuGiamGiaRepository phieuGiamGiaRepository;
    private final HoaDonChiTietRepository hoaDonChiTietRepository;
    private final ThanhToanRepository thanhToanRepository;
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;

    //  Mapper
    private final HoaDonShortResponseMapper hoaDonShortResponseMapper;
    private final HoaDonResponseMapper hoaDonResponseMapper;
    private final HoaDonRequestMapper hoaDonRequestMapper;
    private final SanPhamShortResponseMapper sanPhamShortResponseMapper;
    private final HoaDonChiTietResponseMapper hoaDonChiTietResponseMapper;

    private final KhachHangResponseMapper khachHangResponseMapper;
    private final SPCTResponseMapper sanPhamChiTietResponseMapper;

    private final PhieuGiamGiaResponseMapper phieuGiamGiaResponseMapper;

    public OnlineSalesService(HoaDonRepository hoaDonRepository, SanPhamRepository sanPhamRepository, SanPhamChiTietRepository sanPhamChiTietRepository, PhieuGiamGiaRepository phieuGiamGiaRepository, HoaDonChiTietRepository hoaDonChiTietRepository, ThanhToanRepository thanhToanRepository, KhachHangRepository khachHangRepository, NhanVienRepository nhanVienRepository, HoaDonShortResponseMapper hoaDonShortResponseMapper, HoaDonResponseMapper hoaDonResponseMapper, HoaDonRequestMapper hoaDonRequestMapper, SanPhamShortResponseMapper sanPhamShortResponseMapper, HoaDonChiTietResponseMapper hoaDonChiTietResponseMapper, KhachHangResponseMapper khachHangResponseMapper, SPCTResponseMapper sanPhamChiTietResponseMapper, PhieuGiamGiaResponseMapper phieuGiamGiaResponseMapper) {
        this.hoaDonRepository = hoaDonRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.sanPhamChiTietRepository = sanPhamChiTietRepository;
        this.phieuGiamGiaRepository = phieuGiamGiaRepository;
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
        this.thanhToanRepository = thanhToanRepository;
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.hoaDonShortResponseMapper = hoaDonShortResponseMapper;
        this.hoaDonResponseMapper = hoaDonResponseMapper;
        this.hoaDonRequestMapper = hoaDonRequestMapper;
        this.sanPhamShortResponseMapper = sanPhamShortResponseMapper;
        this.hoaDonChiTietResponseMapper = hoaDonChiTietResponseMapper;
        this.khachHangResponseMapper = khachHangResponseMapper;
        this.sanPhamChiTietResponseMapper = sanPhamChiTietResponseMapper;
        this.phieuGiamGiaResponseMapper = phieuGiamGiaResponseMapper;
    }


    public void printCurrentUserRoles() {
        List<String> roles = AuthenticationUtil.getLoggedInUserRoles();
        if (!roles.isEmpty()) {
            System.out.println("Roles của người dùng hiện tại: " + roles);
        } else {
            System.out.println("Người dùng hiện tại không có roles nào hoặc chưa đăng nhập.");
        }
    }

    @Override
    @Transactional
    public HoaDonResponse taoDonHangOnlineMotSanPham(HoaDonBanOnlineRequest hoaDonBanOnlineRequest, String maKhachHang) {
        //Check KH
        KhachHang khachHang = khachHangRepository.timKHTheoMaKH(maKhachHang, StatusEnum.ACTIVE).orElseThrow(
                () -> new BusinessException("Khách hàng không tồn tại")
        );

        // Chech thanh toán
        ThanhToan thanhToan = thanhToanRepository.findThanhToanByPhuongThucThanhToan(hoaDonBanOnlineRequest.getThanhToan()).orElseThrow(
                () -> new BusinessException("Phương thức thanh toán không tồn tại")
        );

        // Check san pham
        List<Long> idSpcts = hoaDonBanOnlineRequest.getHoaDonChiTiets().stream().map(HoaDonChiTietBanOnlineRequest::getSanPhamChiTiet).map(SPCTRequest::getIdSpct).toList();
        if(idSpcts.isEmpty()) {
            throw new BusinessException("Vui lòng chọn sản phẩm cần mua");
        }

        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.getSPCTByIdSPCTAndTrangThai(idSpcts.get(0), StatusSPCT.ACTIVE).orElseThrow(
                () -> new BusinessException("Không tìm thấy sản phẩm cần mua")
        );

        List<Integer> danhSachSoLuongMua = hoaDonBanOnlineRequest.getHoaDonChiTiets().stream().map(HoaDonChiTietBanOnlineRequest::getSoLuong).toList();
        Integer soLuongMua = danhSachSoLuongMua.get(0);
        if(soLuongMua == null){
            throw new BusinessException("Số lượng mua không được trống");
        }
        if(soLuongMua <= 0) {
            throw new BusinessException("Số lượng mua phải lớn hơn 0");
        }

        if(sanPhamChiTiet.getSoLuong() < soLuongMua) {
            throw new BusinessException("Số lượng sản phẩm trong kho không đủ");
        }

        // Set lại số lượng cho sản phẩm chi tiết
        sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - soLuongMua);

        // Set thông tin cho hóa đơn chi tiết
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setTrangThai(StatusHoaDonChiTiet.ACTIVE);
        hoaDonChiTiet.setDonGia(sanPhamChiTiet.getGia());
        hoaDonChiTiet.setSoLuong(soLuongMua);
        hoaDonChiTiet.setTongTien(sanPhamChiTiet.getGia().multiply(BigDecimal.valueOf(soLuongMua)));
        hoaDonChiTiet.setMaHoaDonChiTiet(GenerateCodeRandomUtil.generateProductCode("HDCT", 6));
        HoaDonChiTiet hoaDonChiTietSaved = hoaDonChiTietRepository.save(hoaDonChiTiet);

        // Set thông tinh cho hóa đơn
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        listHDCT.add(hoaDonChiTietSaved);
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(GenerateCodeRandomUtil.generateProductCode("HD", 8));
        hoaDon.setNgayTaoDon(LocalDateTime.now());
        hoaDon.setDiaChiGiaoHang(hoaDonBanOnlineRequest.getDiaChiGiaoHang());
        hoaDon.setSoDienThoaiKhachHang(hoaDonBanOnlineRequest.getSoDienThoaiKhachHang());
        hoaDon.setTenKhachHang(hoaDonBanOnlineRequest.getTenKhachHang());
        hoaDon.setPhiVanChuyen(BigDecimal.valueOf(30000));
        hoaDon.setTrangThai(StatusHoaDon.PENDINGPROCESSING);
        hoaDon.setTongTien(hoaDonChiTiet.getTongTien());
        hoaDon.setHoaDonChiTiet(listHDCT);
        hoaDon.setKhachHang(khachHang);
        hoaDon.setLoaiHoaDon(LoaiHoaDon.ONLINESALES);
        hoaDon.setThanhToan(thanhToan);
        hoaDon.setGhiChu(hoaDonBanOnlineRequest.getGhiChu());
        HoaDon hoaDonSaved = hoaDonRepository.save(hoaDon);
        hoaDonChiTietSaved.setHoaDon(hoaDonSaved);
        return hoaDonResponseMapper.toDTO(hoaDonSaved);
    }


}
