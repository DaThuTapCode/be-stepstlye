package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.kieudegiay.reponse.KieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.kieudegiay.request.KieuDeGiayRequest;
import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacRequest;
import com.okconde.bestepstyle.core.entity.KieuDeGiay;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.KieuDeGiayService;
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

    // phân trang kiểu đế giày
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<KieuDeGiayResponse>>> getPageKieuDeGiay(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<KieuDeGiayResponse> list = kieuDeGiayService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),"Lấy trang thành công", list));

    }

    // thêm kiểu để giầy
    @PostMapping("create-kieu-de-giay")
    public ResponseEntity<ResponseData<KieuDeGiayResponse>> createKieuDeGiay(@RequestBody KieuDeGiayRequest kieuDeGiayRequest){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm kiểu đế giày thành công", kieuDeGiayService.create(kieuDeGiayRequest)));
    }

    // update kiểu đế giầy
    @PutMapping("update-kieu-de-giay")
    public ResponseEntity<ResponseData<KieuDeGiayResponse>> updateKieuDeGiay(
            @RequestBody KieuDeGiayRequest kieuDeGiayRequest,
            @RequestParam Long id
    ){
        KieuDeGiayResponse updateKDG = kieuDeGiayService.update(id, kieuDeGiayRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Cập nhật kiểu đế giày thành công", updateKDG));
    }

    // get by id kiểu đế giày
    @GetMapping("get-by-id")
    public ResponseEntity<ResponseData<KieuDeGiayResponse>> getKieuDeGiayById(@RequestParam Long id) {
        KieuDeGiayResponse kieuDeGiayResponse = kieuDeGiayService.getById(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy kiểu đế giày thành công", kieuDeGiayResponse));
    }

    // delete kiểu đế giày
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteKieuDeGiay(@PathVariable Long id){
        try {
            kieuDeGiayService.delete(id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),"Xóa thành công kiểu đế giày", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage(),null));
        }
    }
}
