package com.okconde.bestepstyle.feature.employeemanagement.controller;

import com.okconde.bestepstyle.core.dto.diachikhachhang.request.DiaChiKhachHangRequest;
import com.okconde.bestepstyle.core.dto.diachikhachhang.response.DiaChiKhachHangResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.employeemanagement.service.DiaChiKhachHangService;
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
 * Created by Quang Minh on 9/25/2024 20:21:44
 *
 * @author Quang Minh
 */
@RestController
@RequestMapping("api/dia-chi-khach-hang")
public class DiaChiKhachHangController {

    private final DiaChiKhachHangService diaChiKhachHangService;

    public DiaChiKhachHangController(DiaChiKhachHangService diaChiKhachHangService) {
        this.diaChiKhachHangService = diaChiKhachHangService;
    }

    // Hàm lấy toàn bộ dữ liệu
    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<DiaChiKhachHangResponse>>> getAllDiaChiKH(){

        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy thành công địa chỉ khách hàng", diaChiKhachHangService.getAll()));

    }

    // Hàm phân trang
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<DiaChiKhachHangResponse>>> getPageDiaChiKH(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){

        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<DiaChiKhachHangResponse> lst = diaChiKhachHangService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang địa chỉ khách hàng thành công", lst));

    }

    // Hàm lấy địa chỉ khách hàng theo id
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<List<DiaChiKhachHangResponse>>> getDiaChiKHById(@PathVariable Long id){

        DiaChiKhachHangResponse dckhr = diaChiKhachHangService.getById(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy địa chỉ khách hàng thành công", dckhr));

    }

    // Hàm thêm 1 dữ liệu mới
    @PostMapping("create")
    public ResponseEntity<ResponseData<DiaChiKhachHangResponse>> createDiaChiKH(
            @RequestBody DiaChiKhachHangRequest request
    ){

        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm địa chỉ khách hàng thành công", diaChiKhachHangService.create(request)));

    }

    // Hàm update địa chỉ KH
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<DiaChiKhachHangResponse>> updateDiaChiKH(
            @RequestBody DiaChiKhachHangRequest request, @PathVariable Long id
    ){

        DiaChiKhachHangResponse updateDCKH = diaChiKhachHangService.update(id, request);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Cập nhật địa chỉ khách hàng thành công", updateDCKH));

    }

    // Hàm xoá địa chỉ KH
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<Void>> deleteDiaChiKH(@PathVariable Long id){

        diaChiKhachHangService.delete(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Xoá địa chỉ khách hàng thành công", null));

    }
}
