package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.dto.kichco.request.KichCoRequest;
import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
import com.okconde.bestepstyle.core.dto.trongluong.request.TrongLuongRequest;
import com.okconde.bestepstyle.core.dto.trongluong.request.TrongLuongSearchRequest;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.TrongLuongService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@RestController
@RequestMapping("api/trong-luong")
public class TrongLuongController {

    private final TrongLuongService trongLuongService;


    public TrongLuongController(TrongLuongService trongLuongService) {
        this.trongLuongService = trongLuongService;
    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<TrongLuongResponse>>> getAllTrongLuong(){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),"Lấy thành công trọng lượng!",trongLuongService.getAll()));
    }

    // phân trang trọng lượng
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<TrongLuongResponse>>> getPageTrongLuong(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<TrongLuongResponse> list = trongLuongService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),"Lấy trang thành công", list));

    }

    // thêm trong lượng
    @PostMapping("create-trong-luong")
    public ResponseEntity<ResponseData<TrongLuongResponse>> createTrongLuong(@RequestBody @Valid TrongLuongRequest trongLuongRequest){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm trọng lượng thành công", trongLuongService.create(trongLuongRequest)));
    }

    // update trọng lượng
    @PutMapping("update-trong-luong/{id}")
    public ResponseEntity<ResponseData<TrongLuongResponse>> updateTrongLuong(
            @PathVariable Long id,
            @RequestBody TrongLuongRequest trongLuongRequest
    ) {
        TrongLuongResponse trongLuongResponse = trongLuongService.update(id, trongLuongRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Update trọng lượng thành công",
                        trongLuongResponse)
        );
    }

    // get by id trọng lượng
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<?>> getTrongLuongById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(new ResponseData<>(
                HttpStatus.OK.value(),
                "Lấy thành trọng lượng với id: " + id,
                trongLuongService.getById(id)));
    }

    // delete trong lượng
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteTrongLuong(@PathVariable Long id){
        try {
            trongLuongService.delete(id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),"Xóa thành công trọng lượng", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage(),null));
        }
    }

    // Hàm phân trang
    @PostMapping("search")
    public ResponseEntity<ResponseData<Page<TrongLuongResponse>>> getPageTrongLuong(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) TrongLuongSearchRequest trongLuongSearchRequest

    ){

        Page<TrongLuongResponse> page = trongLuongService.searchPageTrongLuong(pageable, trongLuongSearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang trọng lượng thành công", page));

    }
}
