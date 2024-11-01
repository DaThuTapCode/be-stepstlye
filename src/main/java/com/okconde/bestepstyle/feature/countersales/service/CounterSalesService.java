package com.okconde.bestepstyle.feature.countersales.service;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangResponse;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.entity.NhanVien;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.mapper.hoadon.request.HoaDonRequestMapper;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonResponseMapper;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonShortResponseMapper;
import com.okconde.bestepstyle.core.mapper.sanpham.response.SanPhamShortResponseMapper;
import com.okconde.bestepstyle.core.repository.*;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    //  Mapper
    private final HoaDonShortResponseMapper hoaDonShortResponseMapper;
    private final HoaDonResponseMapper hoaDonResponseMapper;
    private final HoaDonRequestMapper hoaDonRequestMapper;
    private final SanPhamShortResponseMapper sanPhamShortResponseMapper;
    /**Constructor*/
    public CounterSalesService(
            HoaDonRepository hoaDonRepository,
            SanPhamRepository sanPhamRepository,
            SanPhamChiTietRepository sanPhamChiTietRepository,
            PhieuGiamGiaRepository phieuGiamGiaRepository,
            HoaDonChiTietRepository hoaDonChiTietRepository,
            ThanhToanRepository thanhToanRepository,
            KhachHangRepository khachHangRepository,
            NhanVienRepository nhanVienRepository,
            HoaDonShortResponseMapper hoaDonShortResponseMapper, HoaDonResponseMapper hoaDonResponseMapper,
            SanPhamShortResponseMapper sanPhamShortResponseMapper,
            HoaDonRequestMapper hoaDonRequestMapper
    ) {
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
        this.sanPhamShortResponseMapper = sanPhamShortResponseMapper;
        this.hoaDonRequestMapper = hoaDonRequestMapper;
    }

    /**Hàm lấy danh sách hóa đơn chờ*/
    @Override
    public List<HoaDonResponse> geListPendingInvoiceCounterSales() {
        List<HoaDon> listPendingInvoice = hoaDonRepository.getHoaDonByStatus(StatusHoaDon.PENDING, "COUNTERSALES");
        return hoaDonResponseMapper.listToDTO(listPendingInvoice);
    }

    /**
     * Hàm tạo hóa đơn chờ cho bán hàng tại quầy
     *
     * @param hoaDonRequest đối tượng {@link HoaDonRequest} hứng dữ liệu
     */
    @Override
    @Transactional
    public HoaDonShortResponse createNewPendingInvoiceCounterSales(HoaDonRequest hoaDonRequest) {
        //Kiểm tra số lượng hóa đơn chờ thanh toán tại quầy
        List<HoaDon> listPendingInvoice = hoaDonRepository.getHoaDonByStatus(StatusHoaDon.PENDING, "COUNTERSALES");
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
        hoaDonNew.setLoaiHoaDon("COUNTERSALES");
        hoaDonNew.setTongTien(BigDecimal.ZERO);
        hoaDonNew.setTongTienSauGiam(BigDecimal.ZERO);
        HoaDon hoaDonSaved = hoaDonRepository.save(hoaDonNew);
        return hoaDonShortResponseMapper.toDTO(hoaDonSaved);
    }

    /**
     * Hàm lấy sản phẩm chi tiết lên
     */
    @Override
    public Page<SPCTResponse> getPageProductDetail() {
        return null;
    }

    /**
     * Hàm lấy danh sách hóa đơn chi tiết by id hóa đơn
     *
     * @param idHoaDon của các HDCT cần lấy
     * @implNote Anh Tuấn thực hiện phần này
     */
    @Override
    public List<HoaDonChiTietResponse> geListHoaDonChiTietCounterSales(Long idHoaDon) {
        return List.of();
    }

    /**
     * Hàm lấy danh sách khách hành
     *
     * @implNote Minh thực hiện
     */
    @Override
    public List<KhachHangResponse> getListKhachHangCounterSales() {
        return List.of();
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
    public Boolean cancelPendingInvoiceCounterSales(Long idHoaDon) {
        return null;
    }

    /**
     * Tạo hóa đơn chi tiết mới
     * 1. Gán id hóa đơn
     * 2. Gán id spct
     * 3. Số lượng
     * 4. Tính tổng tiền của hdct theo đơn giá của spct * số lượng nhập vào
     *
     * @param hoaDonChiTietRequest
     * @param idHoaDon
     * @param idSPCT
     */
    @Override
    public HoaDonChiTietResponse createDetailInvoiceCounterSales(HoaDonChiTietRequest hoaDonChiTietRequest, Long idHoaDon, Long idSPCT) {
        return null;
    }


}
