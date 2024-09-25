package com.okconde.bestepstyle.feature.invoicemangerment.controller;

import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.invoicemangerment.service.HoaDonChiTietService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by TuanIf on 9/25/2024 22:43:14
 *
 * @author TuanIf
 */

@RestController
@RequestMapping("api/hoa-don-chi-tiet")

public class HoaDonChiTietController {

    private final HoaDonChiTietService hoaDonChiTietService;


    public HoaDonChiTietController(HoaDonChiTietService hoaDonChiTietService) {
        this.hoaDonChiTietService = hoaDonChiTietService;
    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<HoaDonChiTietResponse>>> getAllHoaDonChiTiet() {
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy thành công HĐCT", hoaDonChiTietService.getAll()));
    }
}
