package com.okconde.bestepstyle.feature.invoicemangerment.controller;

import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietSearchRequest;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.invoicemangerment.service.HoaDonChiTietService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // Hàm phân trang
    @PostMapping("search")
    public ResponseEntity<ResponseData<Page<HoaDonChiTietResponse>>> getPageHoaDonChiTiet(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) HoaDonChiTietSearchRequest hoaDonChiTietSearchRequest

    ){

        Page<HoaDonChiTietResponse> page = hoaDonChiTietService.searchPageHoaDonChiTiet(pageable, hoaDonChiTietSearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang hóa đơn chi tiết thành công", page));

    }

    // Hàm lấy tất cả danh sách hóa đơn chi tiết
    @GetMapping("get-all")
    public ResponseEntity<ResponseData<?>> getAllHoaDonChiTiet() {
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(),
                        "Lấy thành công Hóa Đơn Chi Tiết",
                        hoaDonChiTietService.getAll()));
    }

    // Hàm tạo mới hóa đơn chi tiết
    @PostMapping("create")
    public ResponseEntity<ResponseData<HoaDonChiTietResponse>> createHoaDonChiTiet(
            @RequestBody HoaDonChiTietRequest request
    ) {
        HoaDonChiTietResponse response = hoaDonChiTietService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData<>(HttpStatus.CREATED.value(), "Tạo thành công Hóa Đơn Chi Tiết"
                        , response));

    }

    // Hàm cập nhật hóa đơn chi tiết
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<HoaDonChiTietResponse>> updateHoaDonChiTiet(
            @PathVariable Long id,
            @RequestBody HoaDonChiTietRequest request
    ) {
        HoaDonChiTietResponse response = hoaDonChiTietService.update(id, request);
        return ResponseEntity.ok(new ResponseData<>(
                HttpStatus.OK.value(),
                "Cập nhật thành công Hóa Đơn Chi Tiết",
                response));
    }


    // Hàm xóa hóa đơn chi tiết
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteHoaDonChiTiet(
            @PathVariable Long id
    ) {
        try {
            hoaDonChiTietService.delete(id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                    "Xóa thành công Hóa Đơn Chi  Tiết", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null));
        }
    }

    // Hàm lấy id hóa đơn chi tiết
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<?>> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(new ResponseData<>(
                HttpStatus.OK.value(),
                "Lấy thành công Hóa Đơn Chi Tiết" + id,
                hoaDonChiTietService.getById(id)));
    }

    // Hàm lấy danh sách id hóa đơn
    @GetMapping("by-id-hoa-don/{id}")
    public ResponseEntity<ResponseData<?>> getHDCTByIdHoaDon(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(new ResponseData<>(
                HttpStatus.OK.value(),
                "Lấy thành công Hóa Đơn" + id,
                hoaDonChiTietService.getByIdHoaDon(id)));
    }


}
