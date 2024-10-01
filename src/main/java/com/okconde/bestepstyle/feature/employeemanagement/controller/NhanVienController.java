package com.okconde.bestepstyle.feature.employeemanagement.controller;

import com.okconde.bestepstyle.core.dto.nhanvien.request.NhanVienRequest;
import com.okconde.bestepstyle.core.dto.nhanvien.response.NhanVienResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.employeemanagement.service.NhanVienServive;
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
 * Created by Quang Minh on 9/25/2024 20:20:40
 *
 * @author Quang Minh
 */
@RestController
@RequestMapping("api/nhan-vien")
public class NhanVienController {

    private final NhanVienServive nhanVienServive;

    public NhanVienController(NhanVienServive nhanVienServive) {
        this.nhanVienServive = nhanVienServive;
    }

    // Hàm lấy toàn bộ dữ liệu
    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<NhanVienResponse>>> getAllNhanVien(){

        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy thành công nhân viên", nhanVienServive.getAll()));

    }

    // Hàm phân trang
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<NhanVienResponse>>> getPageNhanVien(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){

        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<NhanVienResponse> lst = nhanVienServive.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang nhân viên thành công", lst));

    }

    // Hàm lấy nhân viên theo id
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<NhanVienResponse>> getNhanVienById(@PathVariable Long id){

        NhanVienResponse nvr = nhanVienServive.getById(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy nhân viên thành công", nvr));

    }

    // Hàm thêm 1 dữ liệu mới
    @PostMapping("create")
    public ResponseEntity<ResponseData<NhanVienResponse>> createNhanVien(@RequestBody NhanVienRequest request){

        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm nhân viên thành công", nhanVienServive.create(request)));

    }

    // Hàm update nhân viên
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<NhanVienResponse>> updateNhanVien(
            @RequestBody NhanVienRequest request, @PathVariable Long id
    ){

        NhanVienResponse updateNV = nhanVienServive.update(id, request);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Cập nhật nhân viên thành công", updateNV));

    }

    // Hàm xoá nhân viên
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<Void>> deleteNhanVien(@PathVariable Long id){

        nhanVienServive.delete(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Xoá nhân viên thành công", null));

    }
}