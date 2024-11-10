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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by Trong Phu on 27/10/2024 22:08
 * Interface kiến trúc service nghiệp vụ bán hàng tại quầy
 * @author Trong Phu
 */
public interface ICounterSalesService {
    /**Hàm lấy danh sách hóa đơn chờ*/
    List<HoaDonResponse> geListPendingInvoiceCounterSales();

    /**
     * Hàm tạo hóa đơn chờ cho bán hàng tại quầy
     * @param hoaDonRequest đối tượng {@link HoaDonRequest} hứng dữ liệu
     * */
    HoaDonShortResponse createNewPendingInvoiceCounterSales(HoaDonRequest hoaDonRequest);


    /**
     * Hàm lấy danh sách hóa đơn chi tiết by id hóa đơn
     * @param idHoaDon của các HDCT cần lấy
     * @implNote Anh Tuấn thực hiện phần này
     * */
    public List<HoaDonChiTietResponse> getListHoaDonChiTietCounterSales(Long idHoaDon);

    /**Hàm lấy danh sách khách hành
     * @implNote Minh thực hiện */
    public Page<KhachHangResponse> getPageKhachHangCounterSales(Pageable pageable, KhachHangSearchRequest khachHangSearchRequest);

    /**
     * Lấy danh sách các thuộc tính cần tìm kiếm
     *
     * @return Page chứa danh sách kết quả
     */
    default Page<SPCTResponse> searchPageSPCTCounterSales(Pageable pageable, SPCTSearchRequest spctSearchRequest) {
        return null;
    }

    /**
     * Lấy danh sách các thuộc tính cần tìm kiếm
     * @implNote TuanIF
     * @return Page chứa danh sách kết quả
     */
    public Page<PhieuGiamGiaResponse> getPagePGGCounterSales(Pageable pageable, PhieuGiamGiaSearchRequest phieuGiamGiaSearchRequest);

    /**
     * Hủy hóa đơn chờ theo id
     * @implNote Mô tả:
     * Chia làm 2 trường hợp hủy hóa đơn chờ
     * TH1: Hóa chưa có sản phẩm bên trong thì xóa thẳng hóa đơn ra khỏi database
     * TH2: Hóa đơn đã có sản phẩm
     * */
    public Boolean cancelPendingInvoiceCounterSales(Long idHoaDon);

    /**
     * Tạo hóa đơn chi tiết mới
     * 1. Gán id hóa đơn
     * 2. Gán id spct
     * 3. Số lượng
     * 4. Tính tổng tiền của hdct theo đơn giá của spct * số lượng nhập vào
     * */
    public HoaDonChiTietResponse createDetailInvoiceCounterSales(HoaDonChiTietRequest hoaDonChiTietRequest);

    /**
     * Hủy hóa đơn chi tiết
     * @param idHDCT id hóa đơn chi tiết cần hủy
     * */
     HoaDonChiTietResponse cancelDetailInvoice(Long idHDCT);


    Boolean updateKHtoHoaDon(Long idHoaDon, Long idKhachHang);

    /**
     * Tạo hàm chuyển trạng thái hóa đơn
     * @implNote TuanInfinity*/
    public HoaDonResponse markInvoiceAsPaid(Long idHoaDon);

    /**
     *Tạo hàm thanh toán chuyeern khoan
     * @implNote TuanInfinity*/
    public Map<String, String> VnpayBankTransferPayment(Long idHoaDon);

    /**
     *Tạo hàm sua PGG
     * @implNote TuanInfinity*/
    Boolean updatePGGtoHoaDon(Long idHoaDon, Long idPhieuGiamGia);

    /**
     * Hàm sửa số lượng SPCT trong HDCT
     *  */
    HoaDonChiTietResponse updateSoLuongSanPhamTrongHDCT(Long idHDCT, int soLuongThayDoi);
}
