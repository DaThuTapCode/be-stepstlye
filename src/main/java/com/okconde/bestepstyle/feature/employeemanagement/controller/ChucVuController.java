package com.okconde.bestepstyle.feature.employeemanagement.controller;

import com.okconde.bestepstyle.core.dto.chucvu.response.ChucVuResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.employeemanagement.service.ChucVuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Quang Minh on 9/25/2024 20:22:03
 *
 * @author Quang Minh
 */
@RestController
@RequestMapping("api/chuc-vu")
public class ChucVuController {

    private final ChucVuService chucVuService;

    public ChucVuController(ChucVuService chucVuService) {
        this.chucVuService = chucVuService;
    }

    // Hàm lấy toàn bộ dữ liệu
    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<ChucVuResponse>>> getAllChucVu() {

        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "Lấy thành công chức vụ", chucVuService.getAll()));

    }

    // Hàm lấy chức vụ theo id
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<ChucVuResponse>> getChucVuById(@PathVariable Long id) {

        ChucVuResponse cvr = chucVuService.getById(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "Lấy chức vụ theo id thành công", cvr));

    }
}
