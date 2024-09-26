package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.TrongLuongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
