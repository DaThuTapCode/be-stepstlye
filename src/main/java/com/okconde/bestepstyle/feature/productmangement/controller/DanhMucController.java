package com.okconde.bestepstyle.feature.productmangement.controller;

import com.okconde.bestepstyle.core.dto.danhmuc.response.DanhMucResponse;
import com.okconde.bestepstyle.core.entity.DanhMuc;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.core.repository.DanhMucRepository;
import com.okconde.bestepstyle.feature.productmangement.service.DanhMucService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 20:21
 *
 * @author Trong Phu
 */
@RestController
@RequestMapping("api/danh-muc")
public class DanhMucController {

    private final DanhMucService danhMucService;


    public DanhMucController(DanhMucService danhMucService) {
        this.danhMucService = danhMucService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<DanhMucResponse>> getDanhMucById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(new ResponseData(
                HttpStatus.OK.value(),
                "Lấy thành danh mục với id: " + id,
                danhMucService.getById(id)));
    }
    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<DanhMucResponse>>> getAllDanhMuc(){
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(),
                        "Lấy thành công danh sách danh mục",
                        danhMucService.getAll()));
    }

}
