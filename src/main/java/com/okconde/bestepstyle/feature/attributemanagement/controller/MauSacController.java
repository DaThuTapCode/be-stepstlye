package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacRequest;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.feature.attributemanagement.service.MauSacService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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
@RequestMapping("api/mau-sac")
public class MauSacController {

    private final MauSacService mauSacService;

    public MauSacController(MauSacService mauSacService) {
        this.mauSacService = mauSacService;
    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<MauSacResponse>>> getAllMauSac(){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy thành công màu sắc", mauSacService.getAll()));
    }

    // phân trang màu sắc
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<MauSacResponse>>> getPageMauSac(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<MauSacResponse> list = mauSacService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),"Lấy trang thành công", list));

    }

    // thêm màu sắc
    @PostMapping("create-mau-sac")
    public ResponseEntity<ResponseData<MauSacResponse>> createMauSac(@RequestBody @Valid MauSacRequest mauSacRequest){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm màu sắc thành công", mauSacService.create(mauSacRequest)));
    }

    // update màu sắc
    @PutMapping("update-mau-sac")
    public ResponseEntity<ResponseData<MauSacResponse>> updateMauSac(
            @RequestBody MauSacRequest mauSacRequest,
            @RequestParam Long id
    ){
        MauSacResponse updateMS = mauSacService.update(id, mauSacRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Cập nhật màu sắc thành công", updateMS));
    }

    // xóa màu sắc
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteMauSac(@PathVariable Long id){
        try {
            mauSacService.delete(id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),"Xóa thành công màu sắc", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage(),null));
        }
    }

    // get by id màu sắc
    @GetMapping("get-by-id")
    public ResponseEntity<ResponseData<MauSacResponse>> getMauSacById(@RequestParam Long id) {
        MauSacResponse mauSac = mauSacService.getById(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy màu sắc thành công", mauSac));
    }

}
