package com.okconde.bestepstyle.feature.countersales.service;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangResponse;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;
import com.okconde.bestepstyle.core.entity.*;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.mapper.hoadon.request.HoaDonRequestMapper;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonShortResponseMapper;
import com.okconde.bestepstyle.core.mapper.hoadonchitiet.response.HoaDonChiTietResponseMapper;
import com.okconde.bestepstyle.core.mapper.sanpham.response.SanPhamShortResponseMapper;
import com.okconde.bestepstyle.core.repository.*;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Trong Phu on 27/10/2024 22:07
 * Service thực thi kiến trúc interface ICounterSalesService
 * @author Trong Phu
 */
@Slf4j
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
    private final HoaDonRequestMapper hoaDonRequestMapper;
    private final SanPhamShortResponseMapper sanPhamShortResponseMapper;
    private final HoaDonChiTietResponseMapper hoaDonChiTietResponseMapper;

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
            HoaDonShortResponseMapper hoaDonShortResponseMapper,
            SanPhamShortResponseMapper sanPhamShortResponseMapper,
            HoaDonRequestMapper hoaDonRequestMapper,
            HoaDonChiTietResponseMapper hoaDonChiTietResponseMapper) {
        this.hoaDonRepository = hoaDonRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.sanPhamChiTietRepository = sanPhamChiTietRepository;
        this.phieuGiamGiaRepository = phieuGiamGiaRepository;
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
        this.thanhToanRepository = thanhToanRepository;
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.hoaDonShortResponseMapper = hoaDonShortResponseMapper;
        this.sanPhamShortResponseMapper = sanPhamShortResponseMapper;
        this.hoaDonRequestMapper = hoaDonRequestMapper;
        this.hoaDonChiTietResponseMapper = hoaDonChiTietResponseMapper;
    }

    @Override
    public List<HoaDonShortResponse> geListPendingInvoiceCounterSales() {
        List<HoaDon> listPendingInvoice = hoaDonRepository.getHoaDonByStatus(StatusHoaDon.PENDING, "COUNTERSALES");
        List<HoaDonShortResponse> listPendingInvoiceResponse = hoaDonShortResponseMapper.listToDTO(listPendingInvoice);
        return listPendingInvoiceResponse;
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

        HoaDon hoaDonNew = hoaDonRequestMapper.toEntity(hoaDonRequest);
        hoaDonNew.setMaHoaDon(GenerateCodeRandomUtil.generateProductCode("HDTQ", 6));
        hoaDonNew.setNgayTaoDon(LocalDateTime.now());
        hoaDonNew.setNhanVien(NhanVien.builder().idNhanVien(1l).build());
        hoaDonNew.setKhachHang(KhachHang.builder().idKhachHang(1l).build());
        hoaDonNew.setTrangThai(StatusHoaDon.PENDING);
        hoaDonNew.setLoaiHoaDon("COUNTERSALES");
        HoaDon hoaDonSaved = hoaDonRepository.save(hoaDonNew);
        return hoaDonShortResponseMapper.toDTO(hoaDonSaved);
    }

    /**
     * Hàm lấy sản phẩm chi tiết lên
     */
    @Override
    public SPCTResponse getProductDetail() {
        return null;
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
        // Kiểm tra xem hóa đơn có tồn tại hay không
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon)
                .orElseThrow(() -> new BusinessException("Hóa đơn không tồn tại"));

        // Kiểm tra xem sản phẩm chi tiết có tồn tại hay không
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(idSPCT)
                .orElseThrow(() -> new BusinessException("Sản phẩm chi tiết không tồn tại"));

        // Tạo hóa đơn chi tiết mới
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setSoLuong(hoaDonChiTietRequest.getSoLuong());

        // Tính tổng tiền = đơn giá sản phẩm chi tiết * số lượng
        BigDecimal tongTien = sanPhamChiTiet.getGia().multiply(BigDecimal.valueOf(hoaDonChiTietRequest.getSoLuong()));
        hoaDonChiTiet.setTongTien(tongTien);

        // Lưu hóa đơn chi tiết vào repository
        HoaDonChiTiet savedHoaDonChiTiet = hoaDonChiTietRepository.save(hoaDonChiTiet);

        // Chuyển đổi và trả về đối tượng HoaDonChiTietResponse
        return hoaDonChiTietResponseMapper.toDTO(savedHoaDonChiTiet);
    }


}
