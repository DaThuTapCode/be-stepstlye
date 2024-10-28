package com.okconde.bestepstyle.feature.countersales.service;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangResponse;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;

import java.util.List;

/**
 * Created by Trong Phu on 27/10/2024 22:08
 * Interface kiến trúc service nghiệp vụ bán hàng tại quầy
 * @author Trong Phu
 */
public interface ICounterSalesService {
    /**Hàm lấy danh sách hóa đơn chờ*/
    public List<HoaDonShortResponse> geListPendingInvoiceCounterSales();

    /**
     * Hàm tạo hóa đơn chờ cho bán hàng tại quầy
     * @param hoaDonRequest đối tượng {@link HoaDonRequest} hứng dữ liệu
     * */
    public HoaDonShortResponse createNewPendingInvoiceCounterSales(HoaDonRequest hoaDonRequest);

    /**Hàm lấy sản phẩm chi tiết lên*/
    public SPCTResponse getProductDetail();

    /**
     * Hàm lấy danh sách hóa đơn chi tiết by id hóa đơn
     * @param idHoaDon của các HDCT cần lấy
     * @implNote Anh Tuấn thực hiện phần này
     * */
    public List<HoaDonChiTietResponse> geListHoaDonChiTietCounterSales(Long idHoaDon);

    /**Hàm lấy danh sách khách hành
     * @implNote Minh thực hiện */
    public List<KhachHangResponse> getListKhachHangCounterSales();

    /**
     * Lấy danh sách các thuộc tính cần tìm kiếm*/

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
    public HoaDonChiTietResponse createDetailInvoiceCounterSales(HoaDonChiTietRequest hoaDonChiTietRequest, Long idHoaDon, Long idSPCT);

}
