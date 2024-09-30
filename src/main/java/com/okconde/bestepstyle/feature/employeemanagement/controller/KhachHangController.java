package com.okconde.bestepstyle.feature.employeemanagement.controller;

import com.okconde.bestepstyle.core.dto.diachikhachhang.request.DiaChiKhachHangRequest;
import com.okconde.bestepstyle.core.dto.diachikhachhang.response.DiaChiKhachHangResponse;
import com.okconde.bestepstyle.core.dto.khachhang.request.KhachHangRequest;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.employeemanagement.service.KhachHangService;
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
 * Created by Quang Minh on 9/25/2024 20:21:16
 *
 * @author Quang Minh
 */
@RestController
@RequestMapping("api/khach-hang")
public class KhachHangController {

    private final KhachHangService khachHangService;

    public KhachHangController(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    // Hàm lấy toàn bộ dữ liệu
    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<KhachHangResponse>>> getAllKH(){

        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy thành công khách hàng", khachHangService.getAll()));

    }

    // Hàm phân trang
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<KhachHangResponse>>> getPageKH(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){

        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<KhachHangResponse> lst = khachHangService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang khách hàng thành công", lst));

    }

    // Hàm lấy khách hàng theo id
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<List<KhachHangResponse>>> getKHById(@PathVariable Long id){

        KhachHangResponse khr = khachHangService.getById(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy khách hàng thành công", khr));

    }

    // Hàm thêm 1 dữ liệu mới
    @PostMapping("create")
    public ResponseEntity<ResponseData<KhachHangResponse>> createKH(
            @RequestBody KhachHangRequest request
    ){

        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm khách hàng thành công", khachHangService.create(request)));

    }

    // Hàm update KH
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<KhachHangResponse>> updateKH(
            @RequestBody KhachHangRequest request, @PathVariable Long id
    ){

        KhachHangResponse updateKH = khachHangService.update(id, request);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Cập nhật khách hàng thành công", updateKH));

    }

    // Hàm xoá địa chỉ KH
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<Void>> deleteKH(@PathVariable Long id){

        khachHangService.delete(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Xoá khách hàng thành công", null));

    }
}
