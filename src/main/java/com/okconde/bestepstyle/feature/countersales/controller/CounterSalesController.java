package com.okconde.bestepstyle.feature.countersales.controller;


import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
import com.okconde.bestepstyle.core.dto.khachhang.request.KhachHangSearchRequest;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangResponse;

import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaSearchRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTSearchRequest;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;

import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;

import com.okconde.bestepstyle.feature.countersales.service.ICounterSalesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Trong Phu on 27/10/2024 22:06
 * Controller bán hàng tại quầy
 * @author Trong Phu
 */
@RestController
@RequestMapping("api/bhtq")
public class CounterSalesController {
    //Service
    private final ICounterSalesService counterSalesService;

    //Constructor
    public CounterSalesController(
            @Qualifier("counterSalesService") ICounterSalesService counterSalesService
    ) {
        this.counterSalesService = counterSalesService;
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }
    /**
     * @apiNote API lấy danh sách hóa đơn chờ thanh toán của loại hóa đơn bán tại quầy
     * GET <a href="http://localhost:8080/api/bhtq/list-pending-invoice">...</a>
     * */
    @GetMapping("/list-pending-invoice")
    public ResponseEntity<ResponseData<List<HoaDonResponse>>> getListPendingInvoice() {
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy danh sách hóa đơn chờ thanh toán thành công",
                        counterSalesService.geListPendingInvoiceCounterSales()));
    }

    /**
     * @apiNote API tạo hóa đơn chờ thanh toán mới cho bán hàng tại quầy POST <a href="http://localhost:8080/api/bhtq/create-pending-invoice-counter-sales">...</a>
     * @param hoaDonRequest hứng dữ liệu*/
    @PostMapping("/create-pending-invoice-counter-sales")
    public ResponseEntity<ResponseData<HoaDonShortResponse>> createPendingInvoiceCounterSales(
            @RequestBody(required = false) HoaDonRequest hoaDonRequest
            ){
                return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(),
                            "Tạo hóa đơn bán hàn tại quầy mới thành công",
                            counterSalesService.createNewPendingInvoiceCounterSales(hoaDonRequest)
                    )
                );
    }

    /**
     * Tìm kiếm PGG theo yêu cầu tìm kiếm và phân trang.
     *
     * @param pageable - thông tin phân trang
     * @return ResponseEntity chứa danh sách kết quả
     */
    @PostMapping("list-coupons")
    public ResponseEntity<ResponseData<List<PhieuGiamGiaResponse>>> getPagePGG(
            @PageableDefault Pageable pageable,
            @RequestBody PhieuGiamGiaSearchRequest phieuGiamGiaSearchRequest

    ){

        Page<PhieuGiamGiaResponse> page = counterSalesService.getPagePGGCounterSales(pageable, phieuGiamGiaSearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang phiếu giảm giá thành công", page));

    }

    /**
     * Tìm kiếm khách hàng theo yêu cầu tìm kiếm và phân trang.
     *
     * @param pageable - thông tin phân trang
     * @return ResponseEntity chứa danh sách kết quả
     */
    @PostMapping("/list-customer")
    public ResponseEntity<ResponseData<List<KhachHangResponse>>> getPageKH(
            @PageableDefault Pageable pageable,
            @RequestBody KhachHangSearchRequest khachHangSearchRequest

    ){

        Page<KhachHangResponse> page = counterSalesService.getPageKhachHangCounterSales(pageable, khachHangSearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang khách hàng thành công", page));

    }

    /**
     * Tìm kiếm màu sắc theo yêu cầu tìm kiếm và phân trang.
     *
     * @param pageable - thông tin phân trang
     * @return ResponseEntity chứa danh sách kết quả
     */
    @PostMapping("/search-spct")
    public ResponseEntity<ResponseData<Page<SPCTResponse>>> searchPageSPCTCounterSales(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) SPCTSearchRequest spctSearchRequest) {
        Page<SPCTResponse> result = counterSalesService.searchPageSPCTCounterSales(pageable, spctSearchRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Tìm kiếm sản phẩm chi tiết thành công thành công",
                        result)
        );
    }

    /**
     * Hủy hóa đơn theo ID.
     *
     * @param id ID của hóa đơn cần hủy
     * @return ResponseEntity chứa thông tin về trạng thái của yêu cầu
     * @throws EntityNotFoundException nếu không tìm thấy hóa đơn với ID đã cho
     */
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<ResponseData<String>> cancelPendingInvoiceCounterSales(@PathVariable Long id) {
        try {
            counterSalesService.cancelPendingInvoiceCounterSales(id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Hủy hóa đơn chờ thành công", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi khi hủy hóa đơn chờ", null));
        }
    }


    /**
     * @apiNote API lấy danh sách hóa đơn chi tiết theo id hóa đơn
     * GET <a href="http://localhost:8080/api/bhtq/{idHoaDon}/hoa-don-chi-tiet">...</a>
     * @param idHoaDon ID của hóa đơn để lấy danh sách chi tiết
     * @return Danh sách chi tiết hóa đơn cho ID hóa đơn được chỉ định
     */
    @GetMapping("/list-invoice-counter-sales/{idHoaDon}")
    public ResponseEntity<ResponseData<List<HoaDonChiTietResponse>>> getListHoaDonChiTietCounterSales(
            @PathVariable Long idHoaDon
    ){
        List<HoaDonChiTietResponse> hoaDonChiTietResponses = counterSalesService.getListHoaDonChiTietCounterSales(idHoaDon);

        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy danh sách hóa đơn chi tiết thành công",
                        hoaDonChiTietResponses
                )
        );
    }

    /**
     * @apiNote API tạo hóa đơn chi tiết mới cho bán hàng tại quầy
     * POST <a href="http://localhost:8080/api/bhtq/{idHoaDon}/create-detail-invoice/{idSPCT}">...</a>
     * @param hoaDonChiTietRequest Dữ liệu chi tiết hóa đơn
     * @return Đối tượng ResponseData chứa thông tin chi tiết hóa đơn mới tạo
     */
    @PostMapping("/create-detail-invoice")
    public ResponseEntity<ResponseData<HoaDonChiTietResponse>> createDetailInvoiceCounterSales(
            @RequestBody @Validated HoaDonChiTietRequest hoaDonChiTietRequest) {

        // Gọi service để tạo hóa đơn chi tiết mới
        HoaDonChiTietResponse hoaDonChiTietResponse = counterSalesService.createDetailInvoiceCounterSales(
                hoaDonChiTietRequest);

        // Trả về ResponseData với trạng thái thành công và thông tin chi tiết hóa đơn
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Thành công",
                        hoaDonChiTietResponse)
        );
    }

    /**
     * @apiNote API hủy hóa đơn chi tiết
     * @param idHdct ID của hóa đơn chi tiết cần hủy
     */
    @PostMapping("/cancel-detail-invoice/{idHdct}")
    public ResponseEntity<ResponseData<HoaDonChiTietResponse>> cancelDetailInvoiceCounterSales(
            @PathVariable Long idHdct
    ) {
        // Gọi service để hủy hóa đơn chi tiết
        HoaDonChiTietResponse hoaDonChiTietResponse = counterSalesService.cancelDetailInvoice(idHdct);

        // Trả về ResponseData với trạng thái thành công và thông tin chi tiết hóa đơn
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Thành công",
                        hoaDonChiTietResponse)
        );
    }


    /**
     * @apiNote API sửa khách hàng trong hoá đơn
     * PUT http://localhost:8080/api/bhtq/update-hoa-don/{{idHoaDon}}/{{idKhachHang}}
     * @param idHoaDon ID của hóa đơn
     * @param idKhachHang ID của khách hàng
     * @return sửa khách hàng trong hoá đơn cho ID hoá đơn được chỉ định
     */
    @PutMapping("update-hoa-don/{idHoaDon}/{idKhachHang}")
    public ResponseEntity<ResponseData<Boolean>> updateCustomerToInvoiceCounterSales(
            @PathVariable Long idHoaDon,
            @PathVariable Long idKhachHang) {

        counterSalesService.updateKHtoHoaDon(idHoaDon, idKhachHang);
        // Trả về ResponseData với trạng thái thành công và thông tin chi tiết hóa đơn
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Cập nhật khách hàng cho hoá đơn thành công",
                        true)
        );
    }

    /**
     * @apiNote API sửa pgg trong hoá đơn
     * @param idHoaDon ID của hóa đơn
     * @param idPhieuGiamGia ID của phiếu giảm giá
     * @return sửa phiếu giảm giá trong hoá đơn cho ID hoá đơn được chỉ định
     */
    @PutMapping("update-coupons/{idHoaDon}/{idPhieuGiamGia}")
    public ResponseEntity<ResponseData<Boolean>> updateCouponsToInvoiceCounterSales(
            @PathVariable Long idHoaDon,
            @PathVariable Long idPhieuGiamGia) {

        counterSalesService.updatePGGtoHoaDon(idHoaDon, idPhieuGiamGia);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Cập nhật PGG cho hoá đơn thành công",
                        true)
        );
    }

    /**
     * @apiNote API chuyển trạng thái hóa đơn sau khi thanh toán
     * @param idHoaDon ID của hóa đơn
     * @return Đối tượng ResponseData chứa thông tin hóa đơn
     */
    @PostMapping("invoice/pay/{idHoaDon}")
    public ResponseEntity<ResponseData<HoaDonResponse>> payInvoice(
            @PathVariable Long idHoaDon
    ){
        HoaDonResponse paidInvoice = counterSalesService.markInvoiceAsPaid(idHoaDon);

        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Hóa đơn được thanh toán thành công",
                        paidInvoice)
        );
    }

    /**
     * @apiNote API thanh toán bằng VNPAY
     * @param idHoaDon ID của hóa đơn
     * @return Đối tượng ResponseData chứa thông tin hóa đơn
     */
    @PostMapping("vnpay-bank-transfer/{idHoaDon}")
    public ResponseEntity<ResponseData<Map<String, String>>> vnpayBankTransfer(
        @PathVariable Long idHoaDon
    ){
        Map<String, String> map = counterSalesService.VnpayBankTransferPayment(idHoaDon);

        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Thanh toán chuyển khoản thành công",
                        map)
        );
    }

    /**
     * @apiNote API sửa số lượng spct trong hdct*/
    @PostMapping("update-quantity-product-detail-in-detail-invoice/{idHdct}/{soLuongThayDoi}")
    public ResponseEntity<ResponseData<HoaDonChiTietResponse>> thayDoiSoLuongSPCTTrongHDCT(
            @PathVariable Long idHdct,
            @PathVariable int soLuongThayDoi) {
        counterSalesService.updateSoLuongSanPhamTrongHDCT(idHdct, soLuongThayDoi);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Sửa số lượng sản phẩm trong hóa đơn chi tiết thành công",
                        null)
        );
    }
    /**
     * @apiNote API hủy phiêu giảm giá
     * @param idPhieuGiamGia
     */
    @PostMapping("/cancel-coupons/{idPhieuGiamGia}")
    public ResponseEntity<ResponseData<PhieuGiamGiaResponse>> cancelCoupons(
            @PathVariable Long idPhieuGiamGia
    ) {
        // Gọi service để hủy PGG
        PhieuGiamGiaResponse phieuGiamGiaResponse = counterSalesService.cancelCouponsCounterSales(idPhieuGiamGia);

        // Trả về ResponseData với trạng thái thành công và thông tin phiếu giảm giá
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Thành công",
                        phieuGiamGiaResponse)
        );
    }

}
