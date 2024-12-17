package com.okconde.bestepstyle.feature.countersales.service;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.dto.khachhang.request.KhachHangSearchRequest;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangResponse;
import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaSearchRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTSearchRequest;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;
import com.okconde.bestepstyle.core.entity.*;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.mapper.hoadon.request.HoaDonRequestMapper;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonResponseMapper;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonShortResponseMapper;
import com.okconde.bestepstyle.core.mapper.khachhang.response.KhachHangResponseMapper;
import com.okconde.bestepstyle.core.mapper.hoadonchitiet.response.HoaDonChiTietResponseMapper;
import com.okconde.bestepstyle.core.mapper.phieugiamgia.response.PhieuGiamGiaResponseMapper;
import com.okconde.bestepstyle.core.mapper.sanpham.response.SanPhamShortResponseMapper;
import com.okconde.bestepstyle.core.mapper.sanphamchitiet.response.SPCTResponseMapper;
import com.okconde.bestepstyle.core.repository.*;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.*;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Trong Phu on 27/10/2024 22:07
 * Service thực thi kiến trúc interface ICounterSalesService
 * @author Trong Phu
 */
@Service
public class CounterSalesService implements ICounterSalesService {

    //  Repository
    private final HoaDonRepository hoaDonRepository;
    private final SanPhamRepository sanPhamRepository;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    private final PhieuGiamGiaRepository phieuGiamGiaRepository;
    private final HoaDonChiTietRepository hoaDonChiTietRepository;
    private final ThanhToanRepository thanhToanRepository;
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final LichSuHoaDonRepository lichSuHoaDonRepository;
    //  Mapper
    private final HoaDonShortResponseMapper hoaDonShortResponseMapper;
    private final HoaDonResponseMapper hoaDonResponseMapper;
    private final HoaDonRequestMapper hoaDonRequestMapper;
    private final SanPhamShortResponseMapper sanPhamShortResponseMapper;
    private final HoaDonChiTietResponseMapper hoaDonChiTietResponseMapper;

    private final KhachHangResponseMapper khachHangResponseMapper;
    private final SPCTResponseMapper sanPhamChiTietResponseMapper;

    private final PhieuGiamGiaResponseMapper phieuGiamGiaResponseMapper;


    /**Constructor*/
    public CounterSalesService(
            HoaDonRepository hoaDonRepository,
            SanPhamRepository sanPhamRepository,
            SanPhamChiTietRepository sanPhamChiTietRepository,
            PhieuGiamGiaRepository phieuGiamGiaRepository,
            HoaDonChiTietRepository hoaDonChiTietRepository,
            ThanhToanRepository thanhToanRepository,
            KhachHangRepository khachHangRepository,
            NhanVienRepository nhanVienRepository, LichSuHoaDonRepository lichSuHoaDonRepository,
            HoaDonShortResponseMapper hoaDonShortResponseMapper,
            HoaDonResponseMapper hoaDonResponseMapper,
            SanPhamShortResponseMapper sanPhamShortResponseMapper,
            HoaDonRequestMapper hoaDonRequestMapper,
            KhachHangResponseMapper khachHangResponseMapper,
            SPCTResponseMapper sanPhamChiTietResponseMapper,
            HoaDonChiTietResponseMapper hoaDonChiTietResponseMapper, PhieuGiamGiaResponseMapper phieuGiamGiaResponseMapper) {
        this.hoaDonRepository = hoaDonRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.sanPhamChiTietRepository = sanPhamChiTietRepository;
        this.phieuGiamGiaRepository = phieuGiamGiaRepository;
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
        this.thanhToanRepository = thanhToanRepository;
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.lichSuHoaDonRepository = lichSuHoaDonRepository;
        this.hoaDonShortResponseMapper = hoaDonShortResponseMapper;
        this.hoaDonResponseMapper = hoaDonResponseMapper;
        this.sanPhamShortResponseMapper = sanPhamShortResponseMapper;
        this.hoaDonRequestMapper = hoaDonRequestMapper;
        this.khachHangResponseMapper = khachHangResponseMapper;
        this.sanPhamChiTietResponseMapper = sanPhamChiTietResponseMapper;
        this.hoaDonChiTietResponseMapper = hoaDonChiTietResponseMapper;
        this.phieuGiamGiaResponseMapper = phieuGiamGiaResponseMapper;
    }

    /**Hàm lấy danh sách hóa đơn chờ*/
    @Override
    public List<HoaDonResponse> geListPendingInvoiceCounterSales() {
        List<HoaDon> listPendingInvoice = hoaDonRepository.getHoaDonByStatus(StatusHoaDon.PENDING, LoaiHoaDon.COUNTERSALES);
        return hoaDonResponseMapper.listToDTO(listPendingInvoice);
    }

    /**
     * Hàm tạo hóa đơn chờ cho bán hàng tại quầy
     *
     * @param hoaDonRequest đối tượng {@link HoaDonRequest} hứng dữ liệu
     */
    @Override
    @Transactional
    public HoaDonShortResponse createNewPendingInvoiceCounterSales(HoaDonRequest hoaDonRequest, String maNV) {

        NhanVien nhanVien = nhanVienRepository.timNVTheoMaNVVaTrangThai(maNV, StatusEnum.ACTIVE).orElseThrow(
                () -> new BusinessException("Tài khoản nhân viên không hợp lệ!")
        );

        ThanhToan thanhToan = thanhToanRepository.findThanhToanByPhuongThucThanhToan(StatusPTTT.CASH).orElseThrow(
                () -> new BusinessException("Không tìm thấy phương thức thanh toán: " + StatusPTTT.CASH)
        );

        //Kiểm tra số lượng hóa đơn chờ thanh toán tại quầy
        List<HoaDon> listPendingInvoice = hoaDonRepository.getHoaDonByStatus(StatusHoaDon.PENDING, LoaiHoaDon.COUNTERSALES);
        if(listPendingInvoice.size() >= 5) {
            throw new BusinessException("Số lượng hóa đơn chờ thanh toán được tạo dưới 5");
        }
        //Khởi tạo đối tượng hóa đơn mới và set các giá trị mặc định ban đầu
        HoaDon hoaDonNew = new HoaDon();
        hoaDonNew.setMaHoaDon(GenerateCodeRandomUtil.generateProductCode("HDTQ", 6));
        hoaDonNew.setNgayTaoDon(LocalDateTime.now());
        hoaDonNew.setNhanVien(NhanVien.builder().idNhanVien(1l).build());
        hoaDonNew.setKhachHang(KhachHang.builder().idKhachHang(1l).build());
        hoaDonNew.setTrangThai(StatusHoaDon.PENDING);
        hoaDonNew.setLoaiHoaDon(LoaiHoaDon.COUNTERSALES);
        hoaDonNew.setThanhToan(thanhToan);
        hoaDonNew.setTongTien(BigDecimal.ZERO);
        hoaDonNew.setTongTienSauGiam(BigDecimal.ZERO);
        hoaDonNew.setPhiVanChuyen(BigDecimal.ZERO);
        HoaDon hoaDonSaved = hoaDonRepository.save(hoaDonNew);

        // Ghi log lịch sử hóa đơn
        LichSuHoaDon lichSuHoaDon = new LichSuHoaDon();
        lichSuHoaDon.setNgayTao(LocalDateTime.now());
        lichSuHoaDon.setHoaDon(hoaDonSaved);
        lichSuHoaDon.setMaLichSuHoaDon(GenerateCodeRandomUtil.generateProductCode("LSHD", 6));
        lichSuHoaDon.setHanhDong("Tạo hóa đơn tại quầy");
        lichSuHoaDon.setTrangThai(StatusEnum.ACTIVE);
        lichSuHoaDon.setNguoiThucHien(nhanVien.getHoTen());
        lichSuHoaDonRepository.save(lichSuHoaDon);

        //Trả dữ liệu
        return hoaDonShortResponseMapper.toDTO(hoaDonSaved);
    }

    /**
     * Hàm lấy danh sách hóa đơn chi tiết by id hóa đơn
     *
     * @param idHoaDon của các HDCT cần lấy
     * @implNote Anh Tuấn thực hiện phần này
     */
    @Override
    public List<HoaDonChiTietResponse> getListHoaDonChiTietCounterSales(Long idHoaDon) {
        // Lấy danh sách HoaDonChiTiet từ repository dựa trên idHoaDon
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietRepository.findHoaDonChiTietByIdHoaDon(idHoaDon);
        return hoaDonChiTietList.isEmpty() ? List.of() : hoaDonChiTietResponseMapper.listToDTO(hoaDonChiTietList);
    }

    /**
     * Hàm lấy danh sách khách hành
     *
     * @implNote Minh thực hiện
     */
    @Override
    public Page<KhachHangResponse> getPageKhachHangCounterSales(Pageable pageable, KhachHangSearchRequest khachHangSearchRequest) {
        Page<KhachHang> khachHangPage = khachHangRepository.searchPageKHByMaAndTenAndSDT
                (pageable, khachHangSearchRequest.getMaKhachHang(), khachHangSearchRequest.getTenKhachHang(), khachHangSearchRequest.getSoDienThoai());
        return khachHangPage.map(khachHangResponseMapper::toDTO);
    }

    /**
     * Hàm lấy danh sách phếu giảm gia
     *
     * @implNote TuanIF thực hiện
     */
    @Override
    public Page<PhieuGiamGiaResponse> getPagePGGCounterSales(Pageable pageable, PhieuGiamGiaSearchRequest phieuGiamGiaSearchRequest) {
        Page<PhieuGiamGia> phieuGiamGiaPage = phieuGiamGiaRepository.searchPagePhieuGiamGia
                (pageable, phieuGiamGiaSearchRequest.getMaPhieuGiamGia(),
                            phieuGiamGiaSearchRequest.getTenPhieuGiamGia(),
                        phieuGiamGiaSearchRequest.getNgayBatDau(),
                        phieuGiamGiaSearchRequest.getNgayKetThuc(),
                        phieuGiamGiaSearchRequest.getLoaiGiam(),
                        phieuGiamGiaSearchRequest.getTrangThai());
        return phieuGiamGiaPage.map(phieuGiamGiaResponseMapper::toDTO);
    }

    /**
     * Hủy hóa đơn chờ theo id
     *
     * @param idHoaDon
     * @implNote Mô tả:
     * Chia làm 2 trường hợp hủy hóa đơn chờ
     * TH1: Hóa chưa có sản phẩm bên trong thì xóa thẳng hóa đơn ra khỏi database
     * TH2: Hóa đơn đã có sản phẩm
     */
    @Override
    @Transactional
    public Boolean cancelPendingInvoiceCounterSales(Long idHoaDon) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findByIdHoaDonAndTrangThai(idHoaDon, StatusHoaDon.PENDING);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            // Kiểm tra trạng thái hóa đơn trước khi hủy
            if (hoaDon.getTrangThai().equals(StatusHoaDon.CANCELLED)){
                throw new BusinessException("Hoá đơn này đã bị hủy trước đó");
            }

            // Kiểm tra nếu hóa đơn có sản phẩm
            if (!hoaDon.getHoaDonChiTiet().isEmpty()) {
                for (HoaDonChiTiet hoaDonChiTiet : hoaDon.getHoaDonChiTiet()) {
                    // Lấy sản phẩm chi tiết từ id
                    Long idSanPhamChiTiet = hoaDonChiTiet.getSanPhamChiTiet().getIdSpct();
                    Optional<SanPhamChiTiet> optionalSanPhamChiTiet = sanPhamChiTietRepository.findById(idSanPhamChiTiet);

                    if (optionalSanPhamChiTiet.isPresent()) {
                        SanPhamChiTiet sanPhamChiTiet = optionalSanPhamChiTiet.get();
                        // Tăng lại số lượng sản phẩm trong SanPhamChiTiet
                        sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() + hoaDonChiTiet.getSoLuong());

                        // Cập nhật lại số lượng vào kho cho sản phẩm chi tiết
                        sanPhamChiTietRepository.save(sanPhamChiTiet);
                    } else {
                        throw new BusinessException("Không tìm thấy sản phẩm chi tiết với id: " + idSanPhamChiTiet);
                    }
                }
            }

            if(hoaDon.getPhieuGiamGia() != null) {
                hoaDon.getPhieuGiamGia().setSoLuong(hoaDon.getPhieuGiamGia().getSoLuong() + 1);
            }

            hoaDon.setTrangThai(StatusHoaDon.CANCELLED);
            hoaDonRepository.save(hoaDon);
            return true;
        } else {
            throw new BusinessException("Không tìm thấy hóa đơn");
        }
    }



    /**
     * Tạo hóa đơn chi tiết mới
     * 1. Gán id hóa đơn
     * 2. Gán id spct
     * 3. Số lượng
     * 4. Tính tổng tiền của hdct theo đơn giá của spct * số lượng nhập vào
     *
     * @param hoaDonChiTietRequest
     */
    @Override
    @Transactional
    public HoaDonChiTietResponse createDetailInvoiceCounterSales(HoaDonChiTietRequest hoaDonChiTietRequest) {
        // Kiểm tra xem hóa đơn có tồn tại hay không
        HoaDon hoaDon = hoaDonRepository.findById(hoaDonChiTietRequest.getIdHoaDon())
                .orElseThrow(() -> new BusinessException("Hóa đơn không tồn tại"));
        if(hoaDon.getPhieuGiamGia() != null) {
            hoaDon.getPhieuGiamGia().setSoLuong(hoaDon.getPhieuGiamGia().getSoLuong() + 1);
        }

        // Chuyển mất phiếu giảm giá
        hoaDon.setPhieuGiamGia(null);

        // Kiểm tra xem sản phẩm chi tiết có tồn tại hay không
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(hoaDonChiTietRequest.getIdSpct())
                .orElseThrow(() -> new BusinessException("Sản phẩm chi tiết không tồn tại"));

        // Kiểm tra số lượng sản phâm chi tiết còn đủ hay không
        if(sanPhamChiTiet.getSoLuong() < hoaDonChiTietRequest.getSoLuong()) {
            throw new BusinessException("Số lượng sản phẩm trong kho không đủ");
        }

        // Kiểm tra sản phẩm chi tiết đã tồm tại trong hóa đơn chi tiết hay chưa
        // Nếu đã có rồi thì cập nhật số lượng sản phẩm trong hóa đơn chi tiết
        Optional<HoaDonChiTiet> hoaDonChiTietExisting = hoaDonChiTietRepository.getHDCTByIdHoaDonAndIdSPCT(hoaDonChiTietRequest.getIdHoaDon(), hoaDonChiTietRequest.getIdSpct());
        if(hoaDonChiTietExisting.isPresent()) {
            // Set lại đơn giá cho hóa đơn chi tiết đã tồn tại
            hoaDonChiTietExisting.get().setDonGia(sanPhamChiTiet.getGia());

            // Set lại số lượng cho hdct = số lượng ban đầu + số lượng mới
            int soLuong = hoaDonChiTietExisting.get().getSoLuong() + hoaDonChiTietRequest.getSoLuong();
            hoaDonChiTietExisting.get().setSoLuong(soLuong);

            // Tính tổng tiền = đơn giá sản phẩm chi tiết * số lượng
            BigDecimal tongTien = sanPhamChiTiet.getGia().multiply(BigDecimal.valueOf(soLuong));
            hoaDonChiTietExisting.get().setTongTien(tongTien);

            // Set lại số lượng cho sản phẩm chi tiết  = số lượng hiện tại - số lượng thêm vào hóa đơn chi tiết
            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - hoaDonChiTietRequest.getSoLuong());

            // Set lại tổng tiền cho hóa đơn = tổng tiền hiện tại + (đơn giá *  số lượng mới được thêm)
            hoaDon.setTongTien(hoaDon.getTongTien().add(sanPhamChiTiet.getGia().multiply(BigDecimal.valueOf(hoaDonChiTietRequest.getSoLuong()))));
            hoaDon.setTongTienSauGiam(hoaDon.getTongTien());
        }
        //Chưa thì thêm mới hóa đơn chi tiết
        else {
            // Tạo hóa đơn chi tiết mới
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setMaHoaDonChiTiet(GenerateCodeRandomUtil.generateProductCode("HDCT", 6));
            hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
            hoaDonChiTiet.setSoLuong(hoaDonChiTietRequest.getSoLuong());
            hoaDonChiTiet.setTrangThai(StatusHoaDonChiTiet.PENDING);
            // Tính tổng tiền = đơn giá sản phẩm chi tiết * số lượng
            BigDecimal tongTien = sanPhamChiTiet.getGia().multiply(BigDecimal.valueOf(hoaDonChiTietRequest.getSoLuong()));
            hoaDonChiTiet.setDonGia(sanPhamChiTiet.getGia());
            hoaDonChiTiet.setTongTien(tongTien);

            // Set lại số lượng cho sản phẩm chi tiết  = số lượng hiện tại - số lượng thêm vào hóa đơn chi tiết
            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - hoaDonChiTietRequest.getSoLuong());

            // Set lại tổng tiền cho hóa đơn = tổng tiền hiện tại + tổng tiền HDCT
            hoaDon.setTongTien(hoaDon.getTongTien().add(hoaDonChiTiet.getTongTien()));
            hoaDon.setTongTienSauGiam(hoaDon.getTongTien());

            // Lưu hóa đơn chi tiết vào repository
            HoaDonChiTiet savedHoaDonChiTiet = hoaDonChiTietRepository.save(hoaDonChiTiet);

            // Chuyển đổi và trả về đối tượng HoaDonChiTietResponse
            return hoaDonChiTietResponseMapper.toDTO(savedHoaDonChiTiet);
        }
        return null;
    }

    @Override
    @Transactional
    public HoaDonResponse markInvoiceAsPaid(Long idHoaDon, StatusPTTT phuongThucThanhToan, String maGiaoDich, String ghiChu, String maNV) {
        HoaDon hoaDon = hoaDonRepository.findByIdHoaDonAndTrangThai(idHoaDon, StatusHoaDon.PENDING)
                .orElseThrow(() -> new EntityNotFoundException("Hóa Đơn không tồn tại."));

        NhanVien nhanVien = nhanVienRepository.timNVTheoMaNVVaTrangThai(maNV, StatusEnum.ACTIVE).orElseThrow(
                () -> new BusinessException("Nhân viên xác nhận đơn hàng không hợp lệ!")
        );

        //Kiểm tra điều kiệm thêm sản phẩm trước khi ấn thanh toán
        if(hoaDon.getHoaDonChiTiet().isEmpty()) {
            throw new BusinessException("Thêm sản phẩm vào hóa đơn trước khi thanh toán.");
        }

        //Kiểm tra điều kiện phiếu giảm giá trước khi thanh toan
        if (hoaDon.getPhieuGiamGia() != null) {
            PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findByPhieuGiamGiaAndTrangThai(hoaDon.getPhieuGiamGia().getIdPhieuGiamGia(), StatusPhieuGiamGia.ACTIVE)
                    .orElseThrow(() -> new BusinessException("Phiếu giảm giá không tồn tại hoặc đã hết hạn"));

            if (hoaDon.getTongTien().compareTo(hoaDon.getPhieuGiamGia().getGiaTriHoaDonToiThieu()) < 0) {
                throw new BusinessException("Giá trị hóa đơn phải lớn hơn hoặc bằng: "
                        + hoaDon.getPhieuGiamGia().getGiaTriHoaDonToiThieu()
                        + "\n Vui lòng chọn lại phiếu giảm giá phù hợp");
            }

        }

        //Kiểm tra điều kiện hóa đơn đã thanh toán chưa
        if (hoaDon.getTrangThai().equals(StatusHoaDon.PAID)) {
            throw new BusinessException("Hóa đơn này đã được thanh toán, không thể thanh toán lại.");
        }

        ThanhToan thanhToan = thanhToanRepository.findThanhToanByPhuongThucThanhToan(phuongThucThanhToan).orElseThrow(
                () -> new BusinessException("Phương thức thanh toán không tồn tại")
        );
        hoaDon.setThanhToan(thanhToan);
        hoaDon.setMaGiaoDich(maGiaoDich);
        hoaDon.setGhiChu(ghiChu);
        hoaDon.setTrangThai(StatusHoaDon.PAID);
        hoaDonRepository.save(hoaDon);

        // Ghi log lịch sử hóa đơn
        LichSuHoaDon lichSuHoaDon = new LichSuHoaDon();
        lichSuHoaDon.setNgayTao(LocalDateTime.now());
        lichSuHoaDon.setHoaDon(hoaDon);
        lichSuHoaDon.setMaLichSuHoaDon(GenerateCodeRandomUtil.generateProductCode("LSHD", 6));
        lichSuHoaDon.setHanhDong(thanhToan.getPhuongThucThanhToan()
                .equals(StatusPTTT.CASH) ? "Thanh toán hóa đơn " + hoaDon.getMaHoaDon()
                :
                "Thanh toán hóa đơn " + hoaDon.getMaHoaDon()  + " mã giao dịch " + hoaDon.getMaGiaoDich());
        lichSuHoaDon.setTrangThai(StatusEnum.ACTIVE);
        lichSuHoaDon.setNguoiThucHien(nhanVien.getMaNhanVien() + " - " + nhanVien.getHoTen());
        lichSuHoaDonRepository.save(lichSuHoaDon);
        return hoaDonResponseMapper.toDTO(hoaDon);
    }


    /**
     * Hàm lấy phân trang tìm kiếm sản phẩm chi tiết
     *
     * @implNote Ngô Tự thực hiện
     */
    @Override
        public Page<SPCTResponse> searchPageSPCTCounterSales(Pageable pageable, SPCTSearchRequest spctSearchRequest) {
            // Gọi repository để lấy dữ liệu dựa trên điều kiện tìm kiếm
            Page<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietRepository.searchPageSPCT(
                    pageable,
                    spctSearchRequest.getTenSanPham(),
                    spctSearchRequest.getMaSanPham(),
                    spctSearchRequest.getMaSpct(),
                    spctSearchRequest.getIdMauSac(),
                    spctSearchRequest.getIdKichCo(),
                    spctSearchRequest.getIdChatLieu(),
                    spctSearchRequest.getIdTrongLuong(),
                    spctSearchRequest.getIdThuongHieu(),
                    spctSearchRequest.getIdDanhMuc()
            );
            // Sử dụng mapper để chuyển đổi từ SanPhamChiTiet sang SPCTResponse
            return sanPhamChiTiets.map(sanPhamChiTietResponseMapper::toDTO);
        }

    /**
     * Hủy hóa đơn chi tiết
     *
     * @param idHDCT id hóa đơn chi tiết cần hủy
     */
    @Override
    @Transactional
    public HoaDonChiTietResponse cancelDetailInvoice(Long idHDCT) {
        // Lấy hdct by id
        HoaDonChiTiet hoaDonChiTietExisting = hoaDonChiTietRepository.getHDCTByIdAndStatus(idHDCT, StatusHoaDonChiTiet.PENDING)
                .orElseThrow(() -> new BusinessException("Hóa đơn chi tiết không tồn tại"));

        // Lấy hóa đơn theo id và status
        HoaDon hoaDonExisting = hoaDonRepository.findByIdHoaDonAndTrangThai(hoaDonChiTietExisting.getHoaDon().getIdHoaDon(), StatusHoaDon.PENDING)
                .orElseThrow(() -> new BusinessException("Hóa đơn không tồn tại"));

        // Lấy sản phẩm chi tiết theo id
        SanPhamChiTiet sanPhamChiTietExisting = sanPhamChiTietRepository.getSPCTByIdSPCTAndTrangThai(hoaDonChiTietExisting.getSanPhamChiTiet().getIdSpct(), StatusSPCT.ACTIVE)
                .orElseThrow(() -> new BusinessException("Sản phẩm chi tiết không tồn tại"));

        if(hoaDonExisting.getPhieuGiamGia() != null){
            hoaDonExisting.getPhieuGiamGia().setSoLuong(hoaDonExisting.getPhieuGiamGia().getSoLuong() + 1);
        }

        // Set lại số lượng cho sản phẩm chi tiết ( số lượng = số lượng hiện tại + số lượng trong hóa đơn chi tiết)
        int soLuongHienTai = sanPhamChiTietExisting.getSoLuong();
        int soLuongTrongHoaDonChiTiet = hoaDonChiTietExisting.getSoLuong();
        int soLuong = soLuongHienTai + soLuongTrongHoaDonChiTiet;
        sanPhamChiTietExisting.setSoLuong(soLuong);

        // Set lại tổng tiền cho hóa đơn(tổng tiền = tổng tiền hiện tại - tổng tiền của hóa đơn chi tiết)
        BigDecimal tongTienHienTaiCuaHoaDon = hoaDonExisting.getTongTien();
        BigDecimal tongTienCuaHoaDonChiTiet = hoaDonChiTietExisting.getTongTien();
        BigDecimal tongTien = tongTienHienTaiCuaHoaDon.subtract(tongTienCuaHoaDonChiTiet);
        hoaDonExisting.setTongTien(tongTien);
        // Chuyển mất phiếu giảm giá
        hoaDonExisting.setPhieuGiamGia(null);
        hoaDonExisting.setTongTienSauGiam(hoaDonExisting.getTongTien());

        //Xóa cứng hdct
        hoaDonChiTietRepository.delete(hoaDonChiTietExisting);

        return new HoaDonChiTietResponse();
    }
    /**
     * Hàm sửa khách hàng trong hoá đơn
     *
     * @implNote Minh thực hiện
     */
    @Override
    @Transactional
    public Boolean updateKHtoHoaDon(Long idHoaDon, Long idKhachHang) {
        // Kiểm tra xem hóa đơn có tồn tại hay không
        HoaDon hoaDon = hoaDonRepository.findByIdHoaDonAndTrangThai(idHoaDon, StatusHoaDon.PENDING)
                .orElseThrow(() -> new BusinessException("Hóa đơn không tồn tại"));
        // Kiểm tra xem khách hàng có tồn tại hay không
        KhachHang khachHang = khachHangRepository.timKHTheoIDVaTrangThai(idKhachHang, StatusEnum.ACTIVE)
                .orElseThrow(() -> new BusinessException("Khách hàng không tồn tại"));
        hoaDon.setKhachHang(khachHang);
        return true;
    }

    /**
     * Hàm thanh toán VNPAY
     *
     * @implNote TuanIF
     */
    public OutputStream vnpayBankTransferPayment(Long idHoaDon) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon)
                .orElseThrow(() -> new BusinessException("Hóa đơn không tồn tại"));


        String addInfo = URLEncoder.encode("Thanh toan hoa don: " + hoaDon.getMaHoaDon(), StandardCharsets.UTF_8);


        String paymentUrl = "https://img.vietqr.io/image/MB-phuntt139-print.png?addInfo=" + addInfo + "&amount=" + hoaDon.getTongTienSauGiam();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (InputStream inputStream = new URL(paymentUrl).openStream()) {
            IOUtils.copy(inputStream, outputStream); // Copy dữ liệu từ input stream đến output stream
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Không thể tải ảnh từ URL: ");
        }
        return outputStream;
    }

    /**
     * Hàm chọn PGG
     *
     * @implNote TuanIF
     */
    @Override
    @Transactional
    public Boolean updatePGGtoHoaDon(Long idHoaDon, Long idPhieuGiamGia) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon)
                .orElseThrow(() -> new BusinessException("Hóa đơn không tồn tại"));
        // Kiểm tra nếu hóa đơn đã có phiếu giảm giá
        if (hoaDon.getPhieuGiamGia() != null) {
            throw new BusinessException("Chỉ được áp dụng một phiếu giảm giá cho mỗi hóa đơn");
        }
        PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findByPhieuGiamGiaAndTrangThai(idPhieuGiamGia, StatusPhieuGiamGia.ACTIVE)
                .orElseThrow(() -> new BusinessException("Phiếu giảm giá không tồn tại"));

        if(phieuGiamGia.getGiaTriHoaDonToiThieu().compareTo(hoaDon.getTongTien()) > 0) {
                throw new BusinessException("Giá trị hóa đơn phải lớn hơn hoặc bằng: " + phieuGiamGia.getGiaTriHoaDonToiThieu());
        }


        // Tính lại tổng tiền khi phiếu giảm giá là số tiền
        if(phieuGiamGia.getLoaiGiam().equals(StatusLoaiGiam.MONEY)){
            // Kiểm tra nếu giá trị giảm mà lớn hơn hoặc bằng tổng tiền của hóa đơn
            if(hoaDon.getTongTien().compareTo(phieuGiamGia.getGiaTriGiam()) == -1 || hoaDon.getTongTien().compareTo(phieuGiamGia.getGiaTriGiam()) == 0) {
                hoaDon.setTongTienSauGiam(BigDecimal.ZERO);
            }
            // Kiểm tra nếu giá trị giảm nhỏ hơn tổng tiền của hóa đơn
            else {
                BigDecimal tienSauGiamGia = hoaDon.getTongTien().subtract(phieuGiamGia.getGiaTriGiam());
                hoaDon.setTongTienSauGiam(tienSauGiamGia);
            }
        }
        // Tính lại tổng tiền khi phiếu giảm giá là %
        else if (phieuGiamGia.getLoaiGiam().equals(StatusLoaiGiam.PERCENT)) {
            // Tổng tiền sau giảm của háo đơn dùng phiếu giảm giá % sẽ là = (Tổng tiền/100) * số phần trăm giảm
            BigDecimal tienDuocGiam = (hoaDon.getTongTien().divide(BigDecimal.valueOf(100))).multiply(phieuGiamGia.getGiaTriGiam());
            BigDecimal tongTienDuocGiam = hoaDon.getTongTien().subtract((hoaDon.getTongTien().divide(BigDecimal.valueOf(100))).multiply(phieuGiamGia.getGiaTriGiam()));

            if(phieuGiamGia.getGiaTriGiamToiDa().compareTo(tienDuocGiam) < 1) {
                tongTienDuocGiam = hoaDon.getTongTien().subtract(phieuGiamGia.getGiaTriGiamToiDa());
            }

            hoaDon.setTongTienSauGiam(tongTienDuocGiam);
        }else {
            throw new BusinessException("Phiếu giảm giá không hợp lệ");
        }

        // Giảm số lượng phiếu giảm giá
        if (phieuGiamGia.getSoLuong() <= 0) {
            throw new BusinessException("Phiếu giảm giá đã hết");
        }

        phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() - 1);

        hoaDon.setPhieuGiamGia(phieuGiamGia);

        // Lưu lại các thay đổi vào database
        hoaDonRepository.save(hoaDon);
        phieuGiamGiaRepository.save(phieuGiamGia);

        return true;
    }

    /**
     * Hàm sửa số lượng SPCT trong HDCT
     *
     * @param idHDCT cần sửa số lượng
     * @param soLuongThayDoi số lượng cần sửa
     */
    @Override
    @Transactional
    public HoaDonChiTietResponse updateSoLuongSanPhamTrongHDCT(Long idHDCT, int soLuongThayDoi) {

        // Kiểm tra HDCT có tồn tại hay không
        HoaDonChiTiet hoaDonChiTietExisting = hoaDonChiTietRepository.getHDCTByIdAndStatus(idHDCT, StatusHoaDonChiTiet.PENDING)
                .orElseThrow(() -> new BusinessException("Hóa đơn chi tiết này không tồn tại hoặc đã được thanh toán"));

        // Kiểm tra tồn tại hóa đơn
        HoaDon hoaDonExisting = hoaDonRepository.findByIdHoaDonAndTrangThai(
                hoaDonChiTietExisting.getHoaDon().getIdHoaDon(), StatusHoaDon.PENDING)
                .orElseThrow(() -> new BusinessException("Hóa đơn này không tồn tại hoặc đã được thanh toán"));
        if(hoaDonExisting.getPhieuGiamGia() != null) {
            hoaDonExisting.getPhieuGiamGia().setSoLuong(hoaDonExisting.getPhieuGiamGia().getSoLuong() + 1);
        }

        // Kiểm tra tồn tại SPCT
        SanPhamChiTiet sanPhamChiTietExisting = sanPhamChiTietRepository.
                getSPCTByIdSPCTAndTrangThai(hoaDonChiTietExisting.getSanPhamChiTiet().getIdSpct(), StatusSPCT.ACTIVE)
                .orElseThrow(() -> new BusinessException("Sản phẩm chi tiết không tồn tại hoặc đã hết hàng"));

        // Kiểm tra số lượng sản phẩm thay đổi nhỏ hơn 0
        if(soLuongThayDoi <= 0){
            throw new BusinessException("Số lượng thay đổ phải lớn hơn 0");
        }

        // Set lại thông tin cho sản phẩm chi tiết


        // So luong = (soLuongTrongKho + soLuong trong hdct) - soLuongThayDoi
        Integer soLuongTrongKho = sanPhamChiTietExisting.getSoLuong();
        Integer soLuongTrongHDCT = hoaDonChiTietExisting.getSoLuong();
        int soLuongNew = (soLuongTrongKho + soLuongTrongHDCT) - soLuongThayDoi;

        // Kiểm tra số lượng sản phẩm trong kho có đủ hay không
        if(soLuongTrongKho + soLuongTrongHDCT < soLuongThayDoi){
            throw new BusinessException("Số lượng sản phẩm trong kho không đủ");
        }

        sanPhamChiTietExisting.setSoLuong(soLuongNew);


        // Set lại thông tin cho hóa đơn chi tiết
        hoaDonChiTietExisting.setSoLuong(soLuongThayDoi); // set số lượng thay đổi
        hoaDonChiTietExisting.setDonGia(sanPhamChiTietExisting.getGia()); // set đơn giá

        // Set tổng tiền cho hdct = đơn giá spct * với số lượng thay đổi
        BigDecimal tongTienHDCTTruocKhiThayDoiSoLuong = hoaDonChiTietExisting.getTongTien();
        BigDecimal tongTienHDCTSauKhiThayDoiSoLuong = sanPhamChiTietExisting.getGia().multiply(BigDecimal.valueOf(soLuongThayDoi));
        hoaDonChiTietExisting.setTongTien(tongTienHDCTSauKhiThayDoiSoLuong);

        // Set lại thông tin cho hóa đơn
        // Set lại tổng tiền cho hóa đơn(tổng tiền = (tổng tiền hiện tại - tổng tền hdct trước khi đổi số lượng) + tổng tiền của hóa đơn chi tiết sau khi đổi số lượng)
        BigDecimal tongTienHienTaiCuaHoaDon = hoaDonExisting.getTongTien();
        BigDecimal tongTienHDNew = (tongTienHienTaiCuaHoaDon.subtract(tongTienHDCTTruocKhiThayDoiSoLuong)).add(tongTienHDCTSauKhiThayDoiSoLuong);
        hoaDonExisting.setTongTien(tongTienHDNew);
        // Chuyển mất phiếu giảm giá
        hoaDonExisting.setPhieuGiamGia(null);
        hoaDonExisting.setTongTienSauGiam(hoaDonExisting.getTongTien());

        return null;
    }

    @Override
    @Transactional
    public boolean cancelCouponsCounterSales(Long idHoaDon) {
        // Lấy hóa đơn đang chờ thanh toán theo id
        HoaDon hoaDon = hoaDonRepository.findByIdHoaDonAndTrangThai(idHoaDon, StatusHoaDon.PENDING).orElseThrow(
                () -> new BusinessException("Không thể hủy sử dụng phiếu giảm giá ở hóa đơn này")
        );

        // Lấy phiếu giảm giá hiện tại từ hóa đơn
        PhieuGiamGia phieuGiamGia = hoaDon.getPhieuGiamGia();
        if (phieuGiamGia != null) {
            // Tăng lại số lượng phiếu giảm giá lên 1
            phieuGiamGia.setSoLuong(phieuGiamGia.getSoLuong() + 1);

            // Lưu lại thay đổi vào cơ sở dữ liệu
            phieuGiamGiaRepository.save(phieuGiamGia);
        }


        // Cập nhật lại các thông tin của hóa đơn
        hoaDon.setPhieuGiamGia(null);

        hoaDon.setTongTienSauGiam(hoaDon.getTongTien());

        return true;
    }

}




