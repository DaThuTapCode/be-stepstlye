package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuRequest;
import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuSearchRequest;
import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.request.ChatLieuDeGiaySearchRequest;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.response.ChatLieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacRequest;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.ChatLieuService;
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
@RequestMapping("api/chat-lieu")
public class ChatLieuController {

    private final ChatLieuService chatLieuService;

    public ChatLieuController(ChatLieuService chatLieuService) {
        this.chatLieuService = chatLieuService;
    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<ChatLieuResponse>>> getAllChatLieu(){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy thành công chất liệu", chatLieuService.getAll()));
    }

    // phân trang chất liệu
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<ChatLieuResponse>>> getPageChatLieu(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<ChatLieuResponse> list = chatLieuService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy trang thành công",list));
    }

    // thêm chất liệu
    @PostMapping("create-chat-lieu")
    public ResponseEntity<ResponseData<ChatLieuResponse>> createChatLieu(@RequestBody @Valid ChatLieuRequest chatLieuRequest){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm chất liệu thành công", chatLieuService.create(chatLieuRequest)));
    }

    // update chất liệu
    @PutMapping("update-chat-lieu/{id}")
    public ResponseEntity<ResponseData<ChatLieuResponse>> updateChatLieu(
            @PathVariable Long id,
            @RequestBody ChatLieuRequest chatLieuRequest
    ) {
        ChatLieuResponse chatLieuResponse = chatLieuService.update(id, chatLieuRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Update chất liệu thành công",
                        chatLieuResponse)
        );
    }

    // delete chất liệu
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteChatLieu (@PathVariable Long id){
        try {
            chatLieuService.delete(id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),"Xóa thành công chất liệu", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage(),null));
        }
    }

    // get by id chất liệu
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<?>> getChatLieuById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(new ResponseData<>(
                HttpStatus.OK.value(),
                "Lấy thành chất liệu với id: " + id,
                chatLieuService.getById(id)));
    }

    // Hàm phân trang
    @PostMapping("search")
    public ResponseEntity<ResponseData<Page<ChatLieuResponse>>> getPageChatLieu(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) ChatLieuSearchRequest chatLieuSearchRequest

    ){

        Page<ChatLieuResponse> page = chatLieuService.searchPageChatLieu(pageable, chatLieuSearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang chất liệu thành công", page));

    }
}
