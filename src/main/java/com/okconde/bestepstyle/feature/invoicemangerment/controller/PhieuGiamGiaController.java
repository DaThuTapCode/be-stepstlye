package com.okconde.bestepstyle.feature.invoicemangerment.controller;

import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaSearchRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
import com.okconde.bestepstyle.core.entity.PhieuGiamGia;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.invoicemangerment.service.PhieuGiamGiaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by TuanIf on 9/25/2024 20:20:48
 *
 * @author TuanIf
 */

@RestController
@RequestMapping("api/phieu-giam-gia")

public class PhieuGiamGiaController {

    private final PhieuGiamGiaService phieuGiamGiaService;

    public PhieuGiamGiaController(PhieuGiamGiaService phieuGiamGiaService) {
        this.phieuGiamGiaService = phieuGiamGiaService;
    }

    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<PhieuGiamGiaResponse>>> getPagePhieuGiamGia(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 10;
        Pageable pageable = PageRequest.of(current, size);
        List<PhieuGiamGiaResponse> list = phieuGiamGiaService.getPage(pageable);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Láy trang phiếu giảm giá thành công",list)
        );
    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<PhieuGiamGia>>> getAllPhieuGiamGia() {
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy thành công Phiếu Giảm Giá", phieuGiamGiaService.getAll()));
    }

    // Hàm phân trang
    @PostMapping("search")
    public ResponseEntity<ResponseData<Page<PhieuGiamGiaResponse>>> searchPhieuGiamGia(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) PhieuGiamGiaSearchRequest phieuGiamGiaSearchRequest

    ){

        Page<PhieuGiamGiaResponse> page = phieuGiamGiaService.searchPagePhieuGiamGia(pageable, phieuGiamGiaSearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Tìm kiếm Phiếu Giảm Giá thành công", page));

    }

    @PostMapping("create")
    public ResponseEntity<ResponseData<PhieuGiamGiaResponse>> createPhieuGiamGia(
            @RequestBody @Valid PhieuGiamGiaRequest request
    ) {
        PhieuGiamGiaResponse response = phieuGiamGiaService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData<>(HttpStatus.CREATED.value(), "Tạo thành công Phiếu Giảm Giá"
                        , response));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<PhieuGiamGiaResponse>> updatePhieuGiamGia(
            @PathVariable Long id,
            @RequestBody @Valid PhieuGiamGiaRequest request
    ) {
        PhieuGiamGiaResponse response = phieuGiamGiaService.update(id, request);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value()
                , " Cập nhật thành công Phiếu Giảm Giá", response));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<Void>> deletePhieuGiamGia(
            @PathVariable Long id
    ) {
        phieuGiamGiaService.delete(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "Xóa hóa đơn thành công", null));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<PhieuGiamGiaResponse>> getById(
            @PathVariable Long id
    ) {
        PhieuGiamGiaResponse response = phieuGiamGiaService.getById(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Lấy thành công Phiếu Giảm Giá", response));
    }

    //Hàm lấy trạng thái phiếu giảm giá
    @GetMapping("count")
    public ResponseEntity<Map<String, Integer>> getPhieuGiamGiaByStatus() {
        Map<String, Integer> counts = phieuGiamGiaService.getPhieuGiamGiaByStatus();
        return ResponseEntity.ok(counts);
    }

    //Hàm chuyển trạng thái phiếu giảm giá kho hết hạn
    @GetMapping("expired-active-coupons")
    public ResponseEntity<ResponseData<List<PhieuGiamGiaResponse>>> getExpiredActiveCoupons() {
        List<PhieuGiamGiaResponse> expiredCoupons = phieuGiamGiaService.expireCouponsIfNeeded();
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Cập nhật phiếu giảm giá hết hạn thành công",
                        expiredCoupons)
        );
    }

    //Hàm kết thúc nhanh phiếu giảm giá
    @PutMapping("end-promotion/{id}")
    public ResponseEntity<ResponseData<PhieuGiamGiaResponse>> endPromotion(
            @PathVariable Long id
    ){
        PhieuGiamGiaResponse response = phieuGiamGiaService.endPromotion(id);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Kết thúc nhanh phiếu giảm giá thành công!",
                        response)
        );
    }

}
