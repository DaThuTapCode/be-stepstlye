package com.okconde.bestepstyle.feature.countersales.controller;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
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
}