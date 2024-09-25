package com.okconde.bestepstyle.feature.employeemanagement.controller;

import com.okconde.bestepstyle.core.dto.chucvu.response.ChucVuResponse;
import com.okconde.bestepstyle.core.entity.ChucVu;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.feature.employeemanagement.service.ChucVuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<ChucVuResponse>>> getAllChucVu(){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy thành công chức vụ", chucVuService.getAll()));
    }
}
