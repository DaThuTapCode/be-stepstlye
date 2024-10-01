package com.okconde.bestepstyle.feature.productmangement.controller;

import com.okconde.bestepstyle.core.dto.thuonghieu.request.ThuongHieuRequest;
import com.okconde.bestepstyle.core.dto.thuonghieu.response.ThuongHieuResponse;
import com.okconde.bestepstyle.core.entity.ThuongHieu;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.productmangement.service.ThuongHieuService;
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
@RequestMapping(value = "api/thuong-hieu")
public class ThuongHieuController {
    private final ThuongHieuService thuongHieuService;

    public ThuongHieuController(ThuongHieuService thuongHieuService) {
        this.thuongHieuService = thuongHieuService;
    }

    /**
     * @apiNote API lấy {@link ThuongHieu} theo id GET http://localhost:8080/api/thuong-hieu/{{id}}
     * @param id khóa chính của đối tượng {@link ThuongHieu} cần tìm
     * @return data {@link ThuongHieuResponse}
     * */
    @GetMapping(value = "{id}")
    public ResponseEntity<ResponseData<ThuongHieuResponse>> getThuongHieuById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công thương hiệu với id: " + id,
                        thuongHieuService.getById(id))
        );
    }

    /**
     * @apiNote API lấy toàn bộ {@link ThuongHieu} GET http://localhost:8080/api/thuong-hieu/get-all
     * */
    @GetMapping(value = "get-all")
    public ResponseEntity<ResponseData<List<ThuongHieuResponse>>> getAllThuongHieu(){
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công danh sách thương hiệu",
                        thuongHieuService.getAll())
        );
    }



    /**
     * @apiNote API tạo {@link ThuongHieu} mới POST http://localhost:8080/api/thuong-hieu/create
     * @param thuongHieuRequest là đối tượng {@link ThuongHieuRequest} hứng dữ liệu từ client gửi trong body
     * */
    @PostMapping("create")
    public ResponseEntity<ResponseData<ThuongHieuResponse>> createNewThuongHieu(
            @RequestBody ThuongHieuRequest thuongHieuRequest
    ) {
        ThuongHieuResponse thuongHieuResponse = thuongHieuService.create(thuongHieuRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Tạo thương hiệu mới thành công mới thành công",
                        thuongHieuResponse)
        );
    }

    /**
     * @apiNote API update {@link ThuongHieu} PUT http://localhost:8080/api/thuong-hieu/update
     * @param id là khóa chính của đổi tượng {@link ThuongHieu} cần update
     * @param thuongHieuRequest là đối tượng {@link ThuongHieuRequest} hứng dữ liệu từ client gửi trong body
     * */
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<ThuongHieuResponse>> updateDanhMuc(
            @PathVariable Long id,
            @RequestBody ThuongHieuRequest thuongHieuRequest
    ) {
        ThuongHieuResponse thuongHieuResponse = thuongHieuService.update(id, thuongHieuRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Update thương hiệu thành công",
                        thuongHieuResponse)
        );
    }


}
