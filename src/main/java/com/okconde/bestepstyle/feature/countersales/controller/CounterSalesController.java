package com.okconde.bestepstyle.feature.countersales.controller;

import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuSearchRequest;
import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.request.ChatLieuDeGiaySearchRequest;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.response.ChatLieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
import com.okconde.bestepstyle.core.dto.khachhang.request.KhachHangSearchRequest;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangResponse;
import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.dto.kichco.request.KichCoSearchRequest;
import com.okconde.bestepstyle.core.dto.kieudegiay.reponse.KieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.kieudegiay.request.KieuDeGiaySearchRequest;
import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacSearchRequest;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTSearchRequest;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;
import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
import com.okconde.bestepstyle.core.dto.trongluong.request.TrongLuongSearchRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.core.util.groupsvalidation.Create;
import com.okconde.bestepstyle.core.util.groupsvalidation.Update;
import com.okconde.bestepstyle.feature.countersales.service.ICounterSalesService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Hàm phân trang
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
    @PostMapping("/search-thuoc-tinh")
    public ResponseEntity<ResponseData<Page<SPCTResponse>>> searchPageSPCTCounterSales(
            Pageable pageable,
            @RequestBody(required = false) SPCTSearchRequest spctSearchRequest) {
        Page<SPCTResponse> result = counterSalesService.searchPageSPCTCounterSales(pageable, spctSearchRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Tìm kiếm thuộc tính thành công thành công",
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
    @DeleteMapping("/invoices/cancel/{id}")
    public ResponseEntity<ResponseData<String>> cancelInvoice(@PathVariable Long id) {
        try {
            boolean isCanceled = counterSalesService.cancelPendingInvoiceCounterSales(id);
            if (isCanceled) {
                return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Hủy hóa đơn thành công", null));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Không thể hủy hóa đơn", null));
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lỗi hệ thống", null));
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
     * @param idHoaDon ID của hóa đơn
     * @param idSPCT ID của sản phẩm chi tiết
     * @return Đối tượng ResponseData chứa thông tin chi tiết hóa đơn mới tạo
     */
    @PostMapping("/{idHoaDon}/create-detail-invoice/{idSPCT}")
    public ResponseEntity<ResponseData<HoaDonChiTietResponse>> createDetailInvoiceCounterSales(
            @RequestBody @Validated HoaDonChiTietRequest hoaDonChiTietRequest,
            @PathVariable Long idHoaDon,
            @PathVariable Long idSPCT) {

        // Gọi service để tạo hóa đơn chi tiết mới
        HoaDonChiTietResponse hoaDonChiTietResponse = counterSalesService.createDetailInvoiceCounterSales(
                hoaDonChiTietRequest, idHoaDon, idSPCT);

        // Trả về ResponseData với trạng thái thành công và thông tin chi tiết hóa đơn
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Tạo hóa đơn chi tiết mới thành công",
                        hoaDonChiTietResponse)
        );
    }

}
