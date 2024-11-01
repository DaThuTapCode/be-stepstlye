package com.okconde.bestepstyle.feature.countersales.controller;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.core.util.groupsvalidation.Create;
import com.okconde.bestepstyle.core.util.groupsvalidation.Update;
import com.okconde.bestepstyle.feature.countersales.service.ICounterSalesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public ResponseEntity<ResponseData<List<HoaDonShortResponse>>> getListPendingInvoice() {
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
            @RequestBody @Validated(value = {Create.class,}) HoaDonRequest hoaDonRequest
            ){
                return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(),
                            "Tạo hóa đơn bán hàn tại quầy mới thành công",
                            counterSalesService.createNewPendingInvoiceCounterSales(hoaDonRequest)
                    )
                );
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


