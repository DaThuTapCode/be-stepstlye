package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.kieudegiay.reponse.KieuDeGiayResponse;
import com.okconde.bestepstyle.core.entity.KieuDeGiay;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.KieuDeGiayService;
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
@RequestMapping("api/kieu-de-giay")
public class KieuDeGiayController {
    private final KieuDeGiayService kieuDeGiayService;

    public KieuDeGiayController(KieuDeGiayService kieuDeGiayService) {
        this.kieuDeGiayService = kieuDeGiayService;
    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<KieuDeGiayResponse>>> getAllKieuDeGiay(){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),"Lấy thành công kiểu đế giày!", kieuDeGiayService.getAll()));
    }
}
