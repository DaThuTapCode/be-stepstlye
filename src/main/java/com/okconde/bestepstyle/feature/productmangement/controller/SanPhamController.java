package com.okconde.bestepstyle.feature.productmangement.controller;

import com.okconde.bestepstyle.core.dto.sanpham.response.SanPhamResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.productmangement.service.SanPhamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 20:20
 *
 * @author Trong Phu
 */
@RestController
@RequestMapping(value = "api/san-pham")
public class SanPhamController {
    private final SanPhamService sanPhamService;

    public SanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ResponseData<SanPhamResponse>> getSanPhamById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok
                (new ResponseData<>(
                        HttpStatus.OK.value(),
                        "Lấy thành công sản phẩm với id: " + id,
                        sanPhamService.getById(id)
                ));
    }

    @GetMapping(value = "get-all")
    public ResponseEntity<ResponseData<List<SanPhamResponse>>> getAllSanPham(){
        return ResponseEntity.ok
                (new ResponseData<>(
                        HttpStatus.OK.value(),
                        "Lấy thành công danh sách sản phẩm",
                        sanPhamService.getAll()
                ));
    }
}
