package com.okconde.bestepstyle.feature.productmangement.controller;

import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTRequest;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTSearchRequest;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.productmangement.service.SanPhamChiTietService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    ) {
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công sản phẩm chi tiết với id: " + id,
                        sanPhamChiTietService.getById(id))
        );
    }

    @GetMapping(value = "/lay-theo-id-san-pham/{idSanpham}")
    public ResponseEntity<ResponseData<SPCTResponse>> getSPCTBySanPham(
            @PathVariable Long idSanpham
    ) {
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công danh sách sản phẩm chi tiết với id sản phẩm: " + idSanpham,
                        sanPhamChiTietService.getById(idSanpham))
        );
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<ResponseData<List<SPCTResponse>>> getAllSanPhamChiTiet() {
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công danh sách sản phẩm chi tiết",
                        sanPhamChiTietService.getAll())
        );
    }

    @PostMapping(value = "/create-list/{idSanPham}")
    public ResponseEntity<ResponseData<Boolean>> createSanPhamChiTiet(
            @RequestBody List<SPCTRequest> spctRequest,
            @PathVariable Long idSanPham) {
        sanPhamChiTietService.createListSPCTByIDSP(idSanPham, spctRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Tạo sản phẩm chi tiết thành công",
                        true)
        );
    }

    @PostMapping(value = "/update/{idSpct}")
    public ResponseEntity<ResponseData<Boolean>> updateSanPhamChiTiet(
            @RequestBody SPCTRequest spctRequest,
            @PathVariable Long idSpct) {
        sanPhamChiTietService.update(idSpct, spctRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Chỉnh sửa sản phẩm chi tiết thành công",
                        true)
        );
    }

    @PostMapping(value = "/get-by-list-id")
    public ResponseEntity<ResponseData<List<SPCTResponse>>> getByListId(
            @RequestBody List<Long> listIdSpct
    ) {
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy chi tiết sản phẩm lên giỏ hàng thành công",
                        sanPhamChiTietService.getSPCTByListId(listIdSpct))
        );
    }

    @PostMapping(value = "/get-by-id-san-pham/{idSanPham}")
    public ResponseEntity<ResponseData<Page<SPCTResponse>>> getPageBySanPham(
            @PathVariable Long idSanPham,
            @RequestBody(required = false) SPCTSearchRequest spctSearchRequest,
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công phân trang spct theo id sản phẩm",
                        sanPhamChiTietService.getPageSpctByIdSanPham(idSanPham, spctSearchRequest, pageable))
        );
    }


    @PostMapping(value = "/update-anh/{idSanPham}/{idMauSac}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseData<Boolean>> updateAnhSanPhamChiTiet(
            @PathVariable Long idSanPham,
            @PathVariable Long idMauSac,
            @RequestParam("file") MultipartFile file) throws IOException {
            // Xử lý file ảnh
            sanPhamChiTietService.updateImage(idSanPham, idMauSac, file);

            return ResponseEntity.ok(
                    new ResponseData<>(HttpStatus.OK.value(),
                            "Cập nhật ảnh sản phẩm chi tiết thành công",
                            true)
            );
    }

}
