package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuRequest;
import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.dto.kichco.request.KichCoRequest;
import com.okconde.bestepstyle.core.dto.kichco.request.KichCoSearchRequest;
import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacRequest;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.KichCoService;
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

    /**
     * @apiNote: api phân trang kích cỡ
     */
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<KichCoResponse>>> getPageKichCo(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<KichCoResponse> list = kichCoService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),"Lấy trang thành công", list));

    }

    /**
     * @apiNote: api thêm kích cỡ
     */
    @PostMapping("create-kich-co")
    public ResponseEntity<ResponseData<KichCoResponse>> createKichCo(@RequestBody KichCoRequest kichCoRequest){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm kích cỡ thành công", kichCoService.create(kichCoRequest)));
    }

    /**
     * @apiNote: api update kích cỡ
     */
    @PutMapping("update-kich-co/{id}")
    public ResponseEntity<ResponseData<KichCoResponse>> updateKichCo(
            @PathVariable Long id,
            @RequestBody KichCoRequest kichCoRequest
    ) {
        KichCoResponse kichCoResponse = kichCoService.update(id, kichCoRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Update kích cỡ thành công",
                        kichCoResponse)
        );
    }

    /**
     * @apiNote: api get by id kích cỡ
     */
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<?>> getKichCoById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(new ResponseData<>(
                HttpStatus.OK.value(),
                "Lấy thành kích cỡ với id: " + id,
                kichCoService.getById(id)));
    }

    /**
     * @apiNote: api delete kích cỡ
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteKichCo(@PathVariable Long id){
        try {
            kichCoService.delete(id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),"Xóa thành công kích cỡ", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage(),null));
        }
    }

    /**
     * @apiNote: api tìm kiếm & phân trang
     */
    @PostMapping("search")
    public ResponseEntity<ResponseData<Page<KichCoResponse>>> getPageKichCo(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) KichCoSearchRequest kichCoSearchRequest

    ){
        Page<KichCoResponse> page = kichCoService.searchPageKichCo(pageable, kichCoSearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang kích cỡ thành công", page));

    }
}
