package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuRequest;
import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.request.ChatLieuDeGiayRequest;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.request.ChatLieuDeGiaySearchRequest;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.response.ChatLieuDeGiayResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.ChatLieuDeGiayService;
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

    // phân trang chất liệu đế giày
    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<ChatLieuDeGiayResponse>>> getPageChatLieuDeGiay(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<ChatLieuDeGiayResponse> list = chatLieuDeGiayService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy trang thành công",list));
    }

    // thêm chất liệu đế giày
    @PostMapping("create-chat-lieu-de-giay")
    public ResponseEntity<ResponseData<ChatLieuDeGiayResponse>> createChatLieuDeGiay(@RequestBody @Valid ChatLieuDeGiayRequest chatLieuDeGiayRequest){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm chất liệu đế giày thành công", chatLieuDeGiayService.create(chatLieuDeGiayRequest)));
    }

    // update chất liệu đế giày
    @PutMapping("update-chat-lieu-de-giay/{id}")
    public ResponseEntity<ResponseData<ChatLieuDeGiayResponse>> updateChatLieuDeGiay(
            @PathVariable Long id,
            @RequestBody ChatLieuDeGiayRequest chatLieuDeGiayRequest
    ) {
        ChatLieuDeGiayResponse chatLieuDeGiayResponse = chatLieuDeGiayService.update(id, chatLieuDeGiayRequest);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(),
                        "Update chất liệu đế giày thành công",
                        chatLieuDeGiayResponse)
        );
    }

    // get by id chất liệu đế giày
    @GetMapping("{id}")
    public ResponseEntity<ResponseData<?>> getChatLieuDeGiayById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(new ResponseData<>(
                HttpStatus.OK.value(),
                "Lấy thành chất liệu đế giày với id: " + id,
                chatLieuDeGiayService.getById(id)));
    }

    // delete chất liệu đế giày
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteChatLieuDeGiay(@PathVariable Long id){
        try {
            chatLieuDeGiayService.delete(id);
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),"Xóa thành công chất liệu đế giày", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), e.getMessage(),null));
        }
    }

    // Hàm phân trang
    @PostMapping("search")
    public ResponseEntity<ResponseData<Page<ChatLieuDeGiayResponse>>> getPageChatLieuDeGiay(
            @PageableDefault Pageable pageable,
            @RequestBody(required = false) ChatLieuDeGiaySearchRequest chatLieuDeGiaySearchRequest

    ){

        Page<ChatLieuDeGiayResponse> page = chatLieuDeGiayService.searchPageChatLieuDeGiay(pageable, chatLieuDeGiaySearchRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Lấy trang chất liệu đế giày thành công", page));

    }
}
