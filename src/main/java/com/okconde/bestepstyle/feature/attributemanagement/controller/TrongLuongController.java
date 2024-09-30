package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.dto.kichco.request.KichCoRequest;
import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
import com.okconde.bestepstyle.core.dto.trongluong.request.TrongLuongRequest;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.TrongLuongService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<ResponseData<TrongLuongResponse>> createTrongLuong(@RequestBody TrongLuongRequest trongLuongRequest){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm trọng lượng thành công", trongLuongService.create(trongLuongRequest)));
    }

    // update trọng lượng
    @PutMapping("update-trong-luong")
    public ResponseEntity<ResponseData<TrongLuongResponse>> updateTrongLuong(
            @RequestBody TrongLuongRequest trongLuongRequest,
            @RequestParam Long id
    ){
        TrongLuongResponse updateTL = trongLuongService.update(id, trongLuongRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Cập nhật trọng lượng thành công", updateTL));
    }

    // get by id trọng lượng
    @GetMapping("get-by-id")
    public ResponseEntity<ResponseData<TrongLuongResponse>> getTrongLuongById(@RequestParam Long id) {
        TrongLuongResponse trongLuongResponse = trongLuongService.getById(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy trọng lượng thành công", trongLuongResponse));
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

}
