package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.KichCoService;
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
@RequestMapping("api/kich-co")
public class KichCoController {
    private final KichCoService kichCoService;

    public KichCoController(KichCoService kichCoService) {
        this.kichCoService = kichCoService;
    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<KichCoResponse>>> getAllKichCo(){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy thành công kích cỡ!",kichCoService.getAll()));
    }
}
