package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuRequest;
import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.request.ChatLieuDeGiayRequest;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.response.ChatLieuDeGiayResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.ChatLieuDeGiayService;
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
@RequestMapping("api/chat-lieu-de-giay")
public class ChatLieuDeGiayController {
    private final ChatLieuDeGiayService chatLieuDeGiayService;

    public ChatLieuDeGiayController(ChatLieuDeGiayService chatLieuDeGiayService) {
        this.chatLieuDeGiayService = chatLieuDeGiayService;
    }


    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<ChatLieuDeGiayResponse>>> getAllChatLieu(){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),"Lấy thành công chất liệu để giày", chatLieuDeGiayService.getAll()));
    }
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<ChatLieuDeGiayResponse>>> getPageChatLieuDeGiay(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<ChatLieuDeGiayResponse> list = chatLieuDeGiayService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy trang thành công",list));
    }

    @PostMapping("create-chat-lieu-de-giay")
    public ResponseEntity<ResponseData<ChatLieuDeGiayResponse>> createChatLieuDeGiay(@RequestBody ChatLieuDeGiayRequest chatLieuDeGiayRequest){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm chất liệu đế giày thành công", chatLieuDeGiayService.create(chatLieuDeGiayRequest)));
    }
    @PutMapping("update-chat-lieu-de-giay")
    public ResponseEntity<ResponseData<ChatLieuDeGiayResponse>> updateChatLieuDeGiay(
            @RequestBody ChatLieuDeGiayRequest chatLieuDeGiayRequest,
            @RequestParam Long id
    ){
        ChatLieuDeGiayResponse updateCLDG = chatLieuDeGiayService.update(id, chatLieuDeGiayRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Cập nhật chất liệu đế giày thành công", updateCLDG));
    }

    @GetMapping("get-by-id")
    public ResponseEntity<ResponseData<ChatLieuDeGiayResponse>> getChatLieuDeGiayById(@RequestParam Long id) {
        ChatLieuDeGiayResponse chatLieuDeGiayResponse = chatLieuDeGiayService.getById(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy chất liệu đế giày thành công", chatLieuDeGiayResponse));
    }
}
