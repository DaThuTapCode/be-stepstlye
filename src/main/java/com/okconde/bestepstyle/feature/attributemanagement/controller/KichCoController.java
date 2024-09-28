package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.dto.kichco.request.KichCoRequest;
import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacRequest;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.KichCoService;
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

    // phân trang kích cỡ
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<KichCoResponse>>> getPageKichCo(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<KichCoResponse> list = kichCoService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),"Lấy trang thành công", list));

    }

    // thêm kích cỡ
    @PostMapping("create-kich-co")
    public ResponseEntity<ResponseData<KichCoResponse>> createKichCo(@RequestBody KichCoRequest kichCoRequest){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm kích cỡ thành công", kichCoService.create(kichCoRequest)));
    }

    // update kích cỡ
    @PutMapping("update-kich-co")
    public ResponseEntity<ResponseData<KichCoResponse>> updateKichCo(
            @RequestBody KichCoRequest kichCoRequest,
            @RequestParam Long id
    ){
        KichCoResponse updateKC = kichCoService.update(id, kichCoRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Cập nhật kích cỡ thành công", updateKC));
    }

    // get by id kích cỡ
    @GetMapping("get-by-id")
    public ResponseEntity<ResponseData<KichCoResponse>> getKichCoById(@RequestParam Long id) {
        KichCoResponse kichCoResponse = kichCoService.getById(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy kích cỡ thành công", kichCoResponse));
    }
}
