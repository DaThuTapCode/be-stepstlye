package com.okconde.bestepstyle.feature.productmangement.controller;

import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTRequest;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.productmangement.service.SanPhamChiTietService;
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
@RequestMapping(value = "api/san-pham-chi-tiet")
public class SanPhamChiTietController {

    private final SanPhamChiTietService sanPhamChiTietService;

    public SanPhamChiTietController(SanPhamChiTietService sanPhamChiTietService) {
        this.sanPhamChiTietService = sanPhamChiTietService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseData<SPCTResponse>> getSanPhamChiTietById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công sản phẩm chi tiết với id: " + id,
                        sanPhamChiTietService.getById(id))
        );
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<ResponseData<List<SPCTResponse>>> getAllSanPhamChiTiet(){
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công danh sách sản phẩm chi tiết",
                        sanPhamChiTietService.getAll())
        );
    }

    @PostMapping(value = "/create-list/{idSanPham}")
    public ResponseEntity<ResponseData<Boolean>> createSanPhamChiTiet(
            @RequestBody List<SPCTRequest> spctRequest,
            @PathVariable Long idSanPham){
        sanPhamChiTietService.createListSPCTByIDSP(idSanPham, spctRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Tạo sản phẩm chi tiết thành công",
                       true)
        );
    }
}
