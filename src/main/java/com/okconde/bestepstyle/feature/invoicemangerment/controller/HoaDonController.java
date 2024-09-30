package com.okconde.bestepstyle.feature.invoicemangerment.controller;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.invoicemangerment.service.HoaDonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by TuanIf on 9/25/2024 20:20:16
 *
 * @author TuanIf
 */

@RestController
@RequestMapping("api/hoa-don")

public class HoaDonController {

    private final HoaDonService hoaDonService;


    public HoaDonController(HoaDonService hoaDonService) {
        this.hoaDonService = hoaDonService;
    }

    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<HoaDonResponse>>> getPageHoaDon(
            @PageableDefault(size = 10) Pageable pageable
    ){
        List<HoaDonResponse> responses =    hoaDonService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Lấy thành công", responses));
    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<HoaDonResponse>>> getAllHoaDon() {
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy thành công Hóa Đơn", hoaDonService.getAll()));
    }

    @PostMapping("create")
    public ResponseEntity<ResponseData<HoaDonResponse>> createHoaDon(
            @RequestBody HoaDonRequest request
    ) {
        HoaDonResponse response = hoaDonService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData<>(HttpStatus.CREATED.value(), "Tạo thành công"
                        , response));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<HoaDonResponse>> updateHoaDon(
            @PathVariable Long id,
            @RequestBody HoaDonRequest request
    ) {
        HoaDonResponse response = hoaDonService.update(id, request);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value()
                , "Cập nhật thành công", response));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteHoaDon(
            @PathVariable Long id
    ) {
        try {
            hoaDonService.delete(id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                    "Xóa thành công", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<HoaDonResponse>> getById(
            @PathVariable Long id
    ){
        HoaDonResponse response = hoaDonService.getById(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Lấy thành công", response));
    }
}
