package com.okconde.bestepstyle.feature.productmangement.controller;

import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.productmangement.service.ThuongHieuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Trong Phu on 25/09/2024 20:20
 *
 * @author Trong Phu
 */
@RestController
@RequestMapping(value = "api/thuong-hieu")
public class ThuongHieuController {
    private final ThuongHieuService thuongHieuService;

    public ThuongHieuController(ThuongHieuService thuongHieuService) {
        this.thuongHieuService = thuongHieuService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ResponseData> getThuongHieuById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công thương hiệu với id: " + id,
                        thuongHieuService.getById(id))
        );
    }

    @GetMapping(value = "get-all")
    public ResponseEntity<ResponseData> getAllThuongHieu(){
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công danh sách thương hiệu",
                        thuongHieuService.getAll())
        );
    }
}
