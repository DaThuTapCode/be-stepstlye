package com.okconde.bestepstyle.feature.invoicemangerment.controller;

import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaSearchRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
import com.okconde.bestepstyle.core.entity.PhieuGiamGia;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.invoicemangerment.service.PhieuGiamGiaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Hàm phân trang
    @PostMapping("search")
    public ResponseEntity<ResponseData<Page<PhieuGiamGiaResponse>>> getPagePhieuGiamGia(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) PhieuGiamGiaSearchRequest phieuGiamGiaSearchRequest

    ){

        Page<PhieuGiamGiaResponse> page = phieuGiamGiaService.searchPagePhieuGiamGia(pageable, phieuGiamGiaSearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang Phiếu Giảm Giá thành công", page));

    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<PhieuGiamGia>>> getAllPhieuGiamGia() {
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy thành công Phiếu Giảm Giá", phieuGiamGiaService.getAll()));
    }

    @PostMapping("create")
    public ResponseEntity<ResponseData<PhieuGiamGiaResponse>> createPhieuGiamGia(
            @RequestBody PhieuGiamGiaRequest request
    ) {
        PhieuGiamGiaResponse response = phieuGiamGiaService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData<>(HttpStatus.CREATED.value(), "Tạo thành công Phiếu Giảm Giá"
                        , response));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<PhieuGiamGiaResponse>> updatePhieuGiamGia(
            @PathVariable Long id,
            @RequestBody PhieuGiamGiaRequest request
    ) {
        PhieuGiamGiaResponse response = phieuGiamGiaService.update(id, request);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value()
                , " Cập nhật thành công Phiếu Giảm Giá", response));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deletePhieuGiamGia(
            @PathVariable Long id
    ) {
        try {
            phieuGiamGiaService.delete(id); //Xóa mềm
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Xóa thành công Phiếu Giảm Giá", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<PhieuGiamGiaResponse>> getById(
            @PathVariable Long id
    ) {
        PhieuGiamGiaResponse response = phieuGiamGiaService.getById(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Lấy thành công Phiếu Giảm Giá", response));
    }

}
