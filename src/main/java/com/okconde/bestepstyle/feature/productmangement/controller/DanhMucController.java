package com.okconde.bestepstyle.feature.productmangement.controller;

import com.okconde.bestepstyle.core.dto.danhmuc.request.DanhMucRequest;
import com.okconde.bestepstyle.core.dto.danhmuc.response.DanhMucResponse;
import com.okconde.bestepstyle.core.entity.DanhMuc;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.productmangement.service.DanhMucService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    /**
     * @apiNote API lấy {@link DanhMuc} theo id GET http://localhost:8080/api/danh-muc/{{id}}
     * @param id khóa chính của đối tượng {@link DanhMuc} cần tìm
     * */
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<?>> getDanhMucById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(new ResponseData<>(
                HttpStatus.OK.value(),
                "Lấy thành danh mục với id: " + id,
                danhMucService.getById(id)));
    }

    /**
     * @apiNote API lấy toàn bộ {@link DanhMuc} GET http://localhost:8080/api/danh-muc/get-all
     * */
    @GetMapping("get-all")
    public ResponseEntity<ResponseData<?>> getAllDanhMuc() {
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công danh sách danh mục",
                        danhMucService.getAll()));
    }

    /**
     * @apiNote API tạo {@link DanhMuc} mới POST http://localhost:8080/api/danh-muc/create
     * @param danhMucRequest là đối tượng {@link DanhMucRequest} hứng dữ liệu từ client gửi trong body
     * */
    @PostMapping("create")
    public ResponseEntity<ResponseData<DanhMucResponse>> createNewDanhMuc(
            @RequestBody DanhMucRequest danhMucRequest
    ) {
        DanhMucResponse danhMucResponse = danhMucService.create(danhMucRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Tạo danh mục mới thành công",
                        danhMucResponse)
        );
    }

    /**
     * @apiNote API update {@link DanhMuc} PUT http://localhost:8080/api/danh-muc/update
     * @param id là khóa chính của đổi tượng {@link DanhMuc} cần update
     * @param danhMucRequest là đối tượng {@link DanhMucRequest} hứng dữ liệu từ client gửi trong body
     * */
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<DanhMucResponse>> updateDanhMuc(
            @PathVariable Long id,
            @RequestBody DanhMucRequest danhMucRequest
    ) {
        DanhMucResponse danhMucResponse = danhMucService.update(id, danhMucRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Update danh mục thành công",
                        danhMucResponse)
        );
    }

}
