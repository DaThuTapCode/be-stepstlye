package com.okconde.bestepstyle.feature.productmangement.controller;

import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamRequest;
import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamSearchRequest;
import com.okconde.bestepstyle.core.dto.sanpham.response.SanPhamResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.productmangement.service.SanPhamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @apiNote API lấy sản phẩm theo id GET <a href="http://localhost:8080/api/san-pham/">...</a>{{id}}
     * @param id Khóa chính của Sản Phẩm
     * */
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

    /**
     * @apiNote API lấy tẩt cả sản phẩm GET <a href="http://localhost:8080/api/san-pham/get-all">...</a>
     * */
    @GetMapping(value = "get-all")
    public ResponseEntity<ResponseData<List<SanPhamResponse>>> getAllSanPham(){
        return ResponseEntity.ok
                (new ResponseData<>(
                        HttpStatus.OK.value(),
                        "Lấy thành công danh sách sản phẩm",
                        sanPhamService.getAll()
                ));
    }

    /**
     * @apiNote API tìm kiếm sản phẩm theo nhiều tham số POST <a href="http://localhost:8080/api/san-pham/search">...</a>*/
    @PostMapping(value = "search")
    public ResponseEntity<ResponseData<Page<SanPhamResponse>>> searchProduct(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) SanPhamSearchRequest sanPhamSearchRequest
            ){
        return ResponseEntity.ok
                (new ResponseData<>(
                        HttpStatus.OK.value(),
                        "Tìm kiếm thành công danh sách sản phẩm",
                        sanPhamService.searchPageProduct(pageable, sanPhamSearchRequest)
                ));
    }

    @PostMapping(value = "create")
    public ResponseEntity<ResponseData<SanPhamResponse>> createNewProduct(
            @RequestBody SanPhamRequest sanPhamRequest
            ){
        return ResponseEntity.ok(
                new ResponseData<>(
                        HttpStatus.OK.value(),
                        "Thêm sản phẩm mới thành công",
                        sanPhamService.create(sanPhamRequest)
                )
        );
    }
}
