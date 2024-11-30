package com.okconde.bestepstyle.feature.onlinesales.service;

import com.okconde.bestepstyle.core.config.auth.AuthenticationUtil;
import com.okconde.bestepstyle.core.config.mail.EmailUtil;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonBanOnlineRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietBanOnlineRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class OnlineSalesService implements IOnlineSalesService {

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
    private final LichSuHoaDonRepository lichSuHoaDonRepository;

    // Util
    private final EmailUtil emailUtil;


    public OnlineSalesService(HoaDonRepository hoaDonRepository, SanPhamRepository sanPhamRepository, SanPhamChiTietRepository sanPhamChiTietRepository, PhieuGiamGiaRepository phieuGiamGiaRepository, HoaDonChiTietRepository hoaDonChiTietRepository, ThanhToanRepository thanhToanRepository, KhachHangRepository khachHangRepository, NhanVienRepository nhanVienRepository, HoaDonShortResponseMapper hoaDonShortResponseMapper, HoaDonResponseMapper hoaDonResponseMapper, HoaDonRequestMapper hoaDonRequestMapper, SanPhamShortResponseMapper sanPhamShortResponseMapper, HoaDonChiTietResponseMapper hoaDonChiTietResponseMapper, KhachHangResponseMapper khachHangResponseMapper, SPCTResponseMapper sanPhamChiTietResponseMapper, PhieuGiamGiaResponseMapper phieuGiamGiaResponseMapper, LichSuHoaDonRepository lichSuHoaDonRepository, EmailUtil emailUtil) {
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
        this.lichSuHoaDonRepository = lichSuHoaDonRepository;
        this.emailUtil = emailUtil;
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
        if (idSpcts.isEmpty()) {
            throw new BusinessException("Vui lòng chọn sản phẩm cần mua");
        }

        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.getSPCTByIdSPCTAndTrangThai(idSpcts.get(0), StatusSPCT.ACTIVE).orElseThrow(
                () -> new BusinessException("Không tìm thấy sản phẩm cần mua")
        );

        List<Integer> danhSachSoLuongMua = hoaDonBanOnlineRequest.getHoaDonChiTiets().stream().map(HoaDonChiTietBanOnlineRequest::getSoLuong).toList();
        Integer soLuongMua = danhSachSoLuongMua.get(0);
        if (soLuongMua == null) {
            throw new BusinessException("Số lượng mua không được trống");
        }
        if (soLuongMua <= 0) {
            throw new BusinessException("Số lượng mua phải lớn hơn 0");
        }

        if (sanPhamChiTiet.getSoLuong() < soLuongMua) {
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

    /**
     * @param hoaDonBanOnlineRequest
     * @param maKhachHang
     */
    @Override
    public void kiemTraSoLuongTruocKhiChuyenSangTrangMuaHang(HoaDonBanOnlineRequest hoaDonBanOnlineRequest, String maKhachHang) {
        // Lấy danh sách các sản phẩm chi tiết từ hoaDonBanOnlineRequest
        List<HoaDonChiTietBanOnlineRequest> hoaDonChiTietList = hoaDonBanOnlineRequest.getHoaDonChiTiets();

        for (HoaDonChiTietBanOnlineRequest hoaDonChiTiet : hoaDonChiTietList) {
            SPCTRequest spctRequest = hoaDonChiTiet.getSanPhamChiTiet();
            Long idSpct = spctRequest.getIdSpct(); // Lấy ID của sản phẩm chi tiết
            int soLuongMua = hoaDonChiTiet.getSoLuong(); // Số lượng mua của sản phẩm

            // Gọi phương thức để lấy số lượng tồn kho của sản phẩm chi tiết từ cơ sở dữ liệu
            int soLuongTonKho = getSoLuongTonKhoByIdSpct(idSpct); // Giả sử có phương thức lấy số lượng tồn kho

            // Kiểm tra xem số lượng mua có vượt quá số lượng tồn kho không
            if (soLuongMua > soLuongTonKho) {
                throw new BusinessException("Số lượng mua vượt quá số lượng tồn kho của sản phẩm chi tiết : " + idSpct);
            }
        }
    }

    /**
     * @return
     */
    @Override
    public List<PhieuGiamGiaResponse> getDanhSachPhieuGiamGia() {
        List<PhieuGiamGiaResponse> listToDTO = phieuGiamGiaResponseMapper.listToDTO(phieuGiamGiaRepository.getPhieuGiamGiaByTrangThai(StatusPhieuGiamGia.ACTIVE));
        return listToDTO;
    }

    /**
     * @param hoaDonBanOnlineRequest
     * @return
     */
    @Override
    @Transactional
    public HoaDonResponse taoDonHangOnline(HoaDonBanOnlineRequest hoaDonBanOnlineRequest) {

        KhachHang khachHang = khachHangRepository.timKHTheoIDVaTrangThai(hoaDonBanOnlineRequest.getKhachHang().getIdKhachHang(), StatusEnum.ACTIVE)
                .orElseThrow(() -> new BusinessException("Khách hàng không tồn tại hoặc đã bị chặn mua hàng"));
        PhieuGiamGia phieuGiamGia = null;
        if (hoaDonBanOnlineRequest.getPhieuGiamGia().getIdPhieuGiamGia() != null && hoaDonBanOnlineRequest.getPhieuGiamGia().getIdPhieuGiamGia() > 0) {
            phieuGiamGia = phieuGiamGiaRepository.findByPhieuGiamGiaAndTrangThai(hoaDonBanOnlineRequest.getPhieuGiamGia().getIdPhieuGiamGia(), StatusPhieuGiamGia.ACTIVE)
                    .orElseThrow(() -> new BusinessException("Phiếu giảm giá không tồn tại hoặc đã kết thúc"));
        }


        ThanhToan thanhToan = thanhToanRepository.findThanhToanByPhuongThucThanhToan(hoaDonBanOnlineRequest.getThanhToan()).orElseThrow(
                () -> new BusinessException("Không tìm thấy phương thức thanh toán")
        );

        HoaDon hoaDonNew = HoaDon.builder()
                .thanhToan(thanhToan)
                .tongTien(hoaDonBanOnlineRequest.getTongTien())
                .tongTienSauGiam(hoaDonBanOnlineRequest.getTongTienSauGiam())
                .khachHang(khachHang)
                .phieuGiamGia(phieuGiamGia)
                .ghiChu(hoaDonBanOnlineRequest.getGhiChu())
                .tenKhachHang(hoaDonBanOnlineRequest.getTenKhachHang())
                .soDienThoaiKhachHang(hoaDonBanOnlineRequest.getSoDienThoaiKhachHang())
                .diaChiGiaoHang(hoaDonBanOnlineRequest.getDiaChiGiaoHang())
                .maHoaDon(GenerateCodeRandomUtil.generateProductCode("HDO", 7))
                .phiVanChuyen(hoaDonBanOnlineRequest.getPhiVanChuyen())
                .ngayTaoDon(LocalDateTime.now())
                .loaiHoaDon(LoaiHoaDon.ONLINESALES)
                .trangThai(StatusHoaDon.PENDINGPROCESSING)
                .build();
        HoaDon hoaDonSaved = hoaDonRepository.save(hoaDonNew);

        List<HoaDonChiTiet> hoaDonChiTietListNew = new ArrayList<>();
        // Kiểm tra tính hợp lệ của hóa đơn chi tiết
        for (HoaDonChiTietBanOnlineRequest hdctol : hoaDonBanOnlineRequest.getHoaDonChiTiets()) {
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.getSPCTByIdSPCTAndTrangThai(hdctol.getSanPhamChiTiet().getIdSpct(), StatusSPCT.ACTIVE).orElseThrow(
                    () -> new BusinessException("Không tìm thấy sản phẩm cần mua"));

            if (sanPhamChiTiet.getSoLuong() < hdctol.getSoLuong()) {
                throw new BusinessException("Số lượng sản phẩm cần mua không đủ");
            }

            if (sanPhamChiTiet.getGia().compareTo(hdctol.getDonGia()) != 0) {
                throw new BusinessException("Sản phẩm đã có sự thay đổi về giá bán vui lòng tải lại trang!");
            }

            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - hdctol.getSoLuong());

            HoaDonChiTiet hoaDonChiTiet = HoaDonChiTiet.builder()
                    .sanPhamChiTiet(sanPhamChiTiet)
                    .donGia(hdctol.getDonGia())
                    .trangThai(StatusHoaDonChiTiet.ACTIVE)
                    .soLuong(hdctol.getSoLuong())
                    .tongTien(sanPhamChiTiet.getGia().multiply(BigDecimal.valueOf(hdctol.getSoLuong())))
                    .maHoaDonChiTiet(GenerateCodeRandomUtil.generateProductCode("HCDT", 6))
                    .hoaDon(hoaDonSaved)
                    .build();
            hoaDonChiTietListNew.add(hoaDonChiTiet);
        }

        hoaDonChiTietRepository.saveAll(hoaDonChiTietListNew);

        LichSuHoaDon lichSuHoaDon = LichSuHoaDon.builder()
                .hoaDon(hoaDonSaved)
                .maLichSuHoaDon(GenerateCodeRandomUtil.generateProductCode("LSHD", 6))
                .ngayTao(LocalDateTime.now())
                .trangThai(StatusEnum.ACTIVE)
                .nguoiThucHien(hoaDonBanOnlineRequest.getKhachHang().getTenKhachHang())
                .hanhDong("Tài khoản khách hàng " + khachHang.getMaKhachHang() + "tạo đơn hàng")
                .build();
        lichSuHoaDonRepository.save(lichSuHoaDon);
        emailUtil.sendBookingEmail("ntpdth2004@gmail.com", "Test subject", "test text");

        return hoaDonResponseMapper.toDTO(hoaDonSaved);
    }

    /**
     * @param idHoaDon
     * @return
     */
    @Override
    public HoaDonResponse huyHoaDonOnlinePhiaKhachHang(Long idHoaDon, String maKH, String lyDoHuy) {
        NhanVien nv = nhanVienRepository.timNVTheoMaNVVaTrangThai(maKH, StatusEnum.ACTIVE).orElseThrow(
                () -> new BusinessException("Nhân viên không hợp lệ")
        );

        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon)
                .orElseThrow(() -> new BusinessException("Không tìm thấy hóa đơn cần đổi trạng thái"));

        // Định nghĩa thứ tự trạng thái
        Map<StatusHoaDon, Integer> trangThaiMap = Map.of(
                StatusHoaDon.CANCELLED, 0,
                StatusHoaDon.PENDINGPROCESSING, 1,
                StatusHoaDon.CONFIRMED, 2,
                StatusHoaDon.SHIPPING, 3,
                StatusHoaDon.DELIVERED, 4,
                StatusHoaDon.PAID, 5
        );

        // Lấy trạng thái hiện tại
        StatusHoaDon trangThaiHienTai = hoaDon.getTrangThai();

        // Kiểm tra nếu trạng thái mới nhỏ hơn hoặc bằng trạng thái hiện tại
        if (trangThaiMap.get(StatusHoaDon.SHIPPING) <= trangThaiMap.get(trangThaiHienTai)) {
            throw new BusinessException("Trạng thái hóa đơn sai tiến trình hiện tại");
        }

        // Cập nhật trạng thái
        hoaDon.setTrangThai(StatusHoaDon.CANCELLED);
        HoaDon hoaDonSaved = hoaDonRepository.save(hoaDon);
        LichSuHoaDon lichSuHoaDon = LichSuHoaDon.builder()
                .hoaDon(hoaDonSaved)
                .maLichSuHoaDon(GenerateCodeRandomUtil.generateProductCode("LSHD", 6))
                .hanhDong("Khách hàng " + maKH + " hủy hóa đơn với lý do: " + lyDoHuy)
                .ngayTao(LocalDateTime.now())
                .nguoiThucHien(nv.getHoTen())
                .trangThai(StatusEnum.ACTIVE)
                .build();
        lichSuHoaDonRepository.save(lichSuHoaDon);
        return null;
    }

    // Phương thức giả lập lấy số lượng tồn kho từ cơ sở dữ liệu
    private int getSoLuongTonKhoByIdSpct(Long idSpct) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.getSPCTByIdSPCTAndTrangThai(idSpct, StatusSPCT.ACTIVE).orElseThrow(() -> new BusinessException("Không tìm thấy sản phẩm cần mua"));
        return sanPhamChiTiet.getSoLuong();
    }


}
