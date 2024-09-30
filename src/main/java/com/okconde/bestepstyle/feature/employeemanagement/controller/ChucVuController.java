package com.okconde.bestepstyle.feature.employeemanagement.controller;

import com.okconde.bestepstyle.core.dto.chucvu.request.ChucVuRequest;
import com.okconde.bestepstyle.core.dto.chucvu.response.ChucVuResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.employeemanagement.service.ChucVuService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy thành công chức vụ", chucVuService.getAll()));

    }

    // Hàm phân trang
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<ChucVuResponse>>> getPageChucVu(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ) {

        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<ChucVuResponse> list = chucVuService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang chức vụ thành công", list));

    }

    // Hàm lấy chức vụ theo id
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<List<ChucVuResponse>>> getChucVuById(@PathVariable Long id) {

        ChucVuResponse cvr = chucVuService.getById(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy chức vụ theo id thành công", cvr));

    }

    // Hàm thêm 1 dữ liệu mới
    @PostMapping("create")
    public ResponseEntity<ResponseData<ChucVuResponse>> createChucVu(@RequestBody ChucVuRequest request) {

        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm chức vụ thành công", chucVuService.create(request)));

    }

    // Hàm cập nhập chức vụ
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<ChucVuResponse>> updateChucVu(
            @RequestBody ChucVuRequest request, @PathVariable Long id
    ){

        ChucVuResponse updateCV = chucVuService.update(id, request);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Cập nhật chức vụ thành công", updateCV));

    }

    // Hàm xoá chức vụ
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<Void>> deleteChucVu(@PathVariable Long id){
        chucVuService.delete(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Xoá chức vụ thành công", null));

    }
}
