package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuRequest;
import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacRequest;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.ChatLieuService;
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
@RequestMapping("api/chat-lieu")
public class ChatLieuController {

    private final ChatLieuService chatLieuService;

    public ChatLieuController(ChatLieuService chatLieuService) {
        this.chatLieuService = chatLieuService;
    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseData<List<ChatLieuResponse>>> getAllChatLieu(){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),"Lấy thành công chất liệu!", chatLieuService.getAll()));
    }

    @GetMapping("get-page")
    public ResponseEntity<ResponseData<List<ChatLieuResponse>>> getPageChatLieu(
            @RequestParam(value = "currentPage", defaultValue = "0") Integer current
    ){
        int size = 5;
        Pageable pageable = PageRequest.of(current, size);
        List<ChatLieuResponse> list = chatLieuService.getPage(pageable);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy trang thành công",list));
    }

    @PostMapping("create-chat-lieu")
    public ResponseEntity<ResponseData<ChatLieuResponse>> createChatLieu(@RequestBody ChatLieuRequest chatLieuRequest){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Thêm chất liệu thành công", chatLieuService.create(chatLieuRequest)));
    }
    @PutMapping("update-chat-lieu")
    public ResponseEntity<ResponseData<ChatLieuResponse>> updateChatLieu(
            @RequestBody ChatLieuRequest chatLieuRequest,
            @RequestParam Long id
    ){
        ChatLieuResponse updateCL = chatLieuService.update(id, chatLieuRequest);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
                "Cập nhật chất liệu thành công", updateCL));
    }
    @DeleteMapping("delete")
    public ResponseEntity<ResponseData<ChatLieuResponse>> deleteChatLieu(@RequestParam Long id) {
        ChatLieuResponse chatLieu = chatLieuService.getById(id);
        if (chatLieu != null) {
            chatLieu.setDeleted(true);  // Đánh dấu là đã xóa mềm
            chatLieuService.create(ChatLieuRequest.builder().build());  // Lưu bản ghi đã cập nhật
            return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(), "Xóa thành công chất liệu"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(HttpStatus.NOT_FOUND.value(), "Chất liệu không tồn tại"));
        }
    }



    @GetMapping("get-by-id")
    public ResponseEntity<ResponseData<ChatLieuResponse>> getChatLieuById(@RequestParam Long id) {
        ChatLieuResponse chatLieuResponse = chatLieuService.getById(id);
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "Lấy chất liệu thành công", chatLieuResponse));
    }

}
