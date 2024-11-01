package com.okconde.bestepstyle.feature.invoicemangerment.controller;

import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.dto.thanhtoan.request.ThanhToanRequest;
import com.okconde.bestepstyle.core.dto.thanhtoan.request.ThanhToanSearchRequest;
import com.okconde.bestepstyle.core.dto.thanhtoan.response.ThanhToanResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.invoicemangerment.service.ThanhToanService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by TuanIf on 10/16/2024 16:28:29
 *
 * @author TuanIf
 */

@RestController
@RequestMapping("api/thanh-toan")

public class ThanhToanController {
    // Service
    private final ThanhToanService thanhToanService;

    public ThanhToanController(ThanhToanService thanhToanService) {
        this.thanhToanService = thanhToanService;
    }

    //Hàm phân trang
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<ThanhToanResponse>>> getPageThanhToan(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 10;
        Pageable pageable = PageRequest.of(current, size);
        List<ThanhToanResponse> list = thanhToanService.getPage(pageable);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Láy trang Thanh toán thành công",list)
        );
    }

    // Hàm phân tìm kiếm
    @PostMapping("search")
    public ResponseEntity<ResponseData<Page<ThanhToanResponse>>> searchThanhToan(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) ThanhToanSearchRequest thanhToanSearchRequest

    ){

        Page<ThanhToanResponse> page = thanhToanService.searchPageThanhToan(pageable, thanhToanSearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Tìm kiếm thanh toán thành công", page));

    }

    // Hàm lấy tất cả danh sách hóa đơn
    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<ThanhToanResponse>>> getAllThanhToan() {
        return ResponseEntity.ok(new ResponseData(
                HttpStatus.OK.value(),
                "Lấy thành công danh sách Thanh Toán",
                thanhToanService.getAll()));
    }

    //Hàm tạo mới Thanh Toán
    @PostMapping("create")
    public ResponseEntity<ResponseData<ThanhToanResponse>> createThanhToan(
            @RequestBody @Valid ThanhToanRequest request
    ) {
        ThanhToanResponse response = thanhToanService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData<>(
                        HttpStatus.CREATED.value(),
                        "Tạo thành công Thanh Toán"
                        , response));
    }

    //Hàm cập nhật Thanh Toán
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<ThanhToanResponse>> updateThanhToan(
            @PathVariable Long id,
            @RequestBody @Valid ThanhToanRequest request
    ) {
        ThanhToanResponse response = thanhToanService.update(id, request);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value()
                , "Cập nhật thành công Thanh Toán", response));
    }

    //Hàm xóa Thanh Toán
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<Void>> deleteThanhToan(
            @PathVariable Long id
    ) {
        thanhToanService.delete(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "Xóa thanh toán thành công", null));
    }

    //Hàm lấy id Thanh Toán
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<ThanhToanResponse>> getThanhToanById(
            @PathVariable Long id
    ){
        ThanhToanResponse response = thanhToanService.getById(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Lấy thành công Thanh Toán", response));
    }
}
