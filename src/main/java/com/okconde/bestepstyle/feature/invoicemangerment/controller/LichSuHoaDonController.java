package com.okconde.bestepstyle.feature.invoicemangerment.controller;

import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.dto.lichsuhoadon.request.LichSuHoaDonRequest;
import com.okconde.bestepstyle.core.dto.lichsuhoadon.request.LichSuHoaDonSearchRequest;
import com.okconde.bestepstyle.core.dto.lichsuhoadon.response.LichSuHoaDonResponse;
import com.okconde.bestepstyle.core.entity.LichSuHoaDon;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.invoicemangerment.service.LichSuHoaDonService;
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
 * Created by TuanIf on 9/25/2024 20:20:39
 *
 * @author TuanIf
 */

@RestController
@RequestMapping("api/lich-su-hoa-don")

public class LichSuHoaDonController {

    private final LichSuHoaDonService lichSuHoaDonService;


    public LichSuHoaDonController(LichSuHoaDonService lichSuHoaDonService) {
        this.lichSuHoaDonService = lichSuHoaDonService;
    }

    //Hàm phân trang
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<LichSuHoaDonResponse>>> getPageLichSuHoaDon(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 10;
        Pageable pageable = PageRequest.of(current, size);
        List<LichSuHoaDonResponse> list = lichSuHoaDonService.getPage(pageable);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Láy trang LSHĐ thành công",list)
        );
    }

    // Hàm phân tìm kiếm
    @PostMapping("search")
    public ResponseEntity<ResponseData<Page<LichSuHoaDonResponse>>> searchLichSuHoaDon(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) LichSuHoaDonSearchRequest lichSuHoaDonSearchRequest

    ){

        Page<LichSuHoaDonResponse> page = lichSuHoaDonService.searchPageLichSuHoaDon(pageable, lichSuHoaDonSearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Tìm kiếm LSHĐ thành công", page));

    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<LichSuHoaDon>>> getAllLichSuHoaDon() {
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy thành công LSHĐ", lichSuHoaDonService.getAll()));
    }

    @GetMapping("create")
    public ResponseEntity<ResponseData<LichSuHoaDonResponse>> createLichSuHoaDon(
            @RequestBody @Valid LichSuHoaDonRequest request
    ) {
        LichSuHoaDonResponse response = lichSuHoaDonService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData<>(HttpStatus.CREATED.value(), "Tạo thành công LSHĐ"
                        , response));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<LichSuHoaDonResponse>> updateLichSuHoaDon(
            @PathVariable Long id,
            @RequestBody @Valid LichSuHoaDonRequest request
    ) {
        LichSuHoaDonResponse response = lichSuHoaDonService.update(id, request);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value()
                , "Cập nhật thành công LSHĐ", response));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<Void>> deleteLichSuHoaDon(
            @PathVariable Long id
    ) {
        lichSuHoaDonService.delete(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "Xóa LSHĐ thành công", null));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<LichSuHoaDonResponse>> getById(
            @PathVariable Long id
    ) {
        LichSuHoaDonResponse response = lichSuHoaDonService.getById(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Lấy thành công LSHĐ", response));
    }
}
