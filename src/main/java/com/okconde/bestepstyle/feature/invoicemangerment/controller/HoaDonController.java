package com.okconde.bestepstyle.feature.invoicemangerment.controller;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonSearchRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.core.util.enumutil.LoaiHoaDon;
import com.okconde.bestepstyle.feature.invoicemangerment.service.HoaDonService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by TuanIf on 9/25/2024 20:20:16
 *
 * @author TuanIf
 */

@RestController
@RequestMapping("api/hoa-don")

public class HoaDonController {

    private final HoaDonService hoaDonService;


    public HoaDonController(HoaDonService hoaDonService) {
        this.hoaDonService = hoaDonService;
    }

    //Hàm phân trang
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<HoaDonResponse>>> getPageHoaDon(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 10;
        Pageable pageable = PageRequest.of(current, size);
        List<HoaDonResponse> list = hoaDonService.getPage(pageable);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Láy trang Hóa Đơn thành công",list)
        );
    }

    // Hàm phân tìm kiếm
    @PostMapping(value = "search")
    public ResponseEntity<ResponseData<Page<HoaDonResponse>>> searchHoaDon(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) HoaDonSearchRequest hoaDonSearchRequest

    ){
        Page<HoaDonResponse> page = hoaDonService.searchPageHoaDon(pageable, hoaDonSearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Tìm kiếm hóa đơn thành công", page));

    }

    // Hàm lấy tất cả danh sách hóa đơn
    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<HoaDonResponse>>> getAllHoaDon() {
        return ResponseEntity.ok(new ResponseData(
                HttpStatus.OK.value(),
                "Lấy thành công Hóa Đơn",
                hoaDonService.getAll()));
    }


    //Hàm tạo mới hóa đơn
    @PostMapping("create")
    public ResponseEntity<ResponseData<HoaDonResponse>> createHoaDon(
            @RequestBody @Valid HoaDonRequest request
    ) {
        HoaDonResponse response = hoaDonService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData<>(
                        HttpStatus.CREATED.value(),
                        "Tạo thành công Hóa Đơn"
                        , response));
    }

    //Hàm cập nhật hóa đơn
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<HoaDonResponse>> updateHoaDon(
            @PathVariable Long id,
            @RequestBody @Valid HoaDonRequest request
    ) {
        HoaDonResponse response = hoaDonService.update(id, request);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value()
                , "Cập nhật thành công Hóa Đơn", response));
    }

    //Hàm xóa hóa đơn
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteHoaDon(
            @PathVariable Long id
    ) {
        hoaDonService.delete(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "Xóa Hóa Đơn thành công", null));
    }

    //Hàm lấy id hóa đơn
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<HoaDonResponse>> getById(
            @PathVariable Long id
    ){
        HoaDonResponse response = hoaDonService.getById(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Lấy thành công Hóa Đơn", response));
    }

    //Hàm lấy trạng thái hóa đơn
    @GetMapping("count")
    public ResponseEntity<Map<String, Integer>> getHoaDonByStatus() {
        Map<String, Integer> counts = hoaDonService.getHoaDonByStatus();
        return ResponseEntity.ok(counts);
    }

    /**
     * @apiNote Api lấy .... */
    @GetMapping("invoice-by-invoice-type")
    public ResponseEntity<ResponseData<List<HoaDonResponse>>> getHoaDonByLoaiHoaDon(
            @RequestParam LoaiHoaDon loaiHoaDon
            ) {
        List<HoaDonResponse> list = hoaDonService.getHoaDonByLoaiHoaDon(loaiHoaDon);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Lấy thành công hóa đơn theo loại hóa đơn",
                        list));
    }

    @GetMapping("/generate-invoice/{idHoaDon}")
    public ResponseEntity<byte[]> generateInvoice(@PathVariable Long idHoaDon) {
        try {
            // Gọi service để tạo PDF
            byte[] pdfBytes = hoaDonService.generateInvoice(idHoaDon);

            // Tạo headers để hiển thị PDF trong trình duyệt
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=hoa_don_" + idHoaDon
                    + ".pdf");

            // Trả về PDF dưới dạng byte[]
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
