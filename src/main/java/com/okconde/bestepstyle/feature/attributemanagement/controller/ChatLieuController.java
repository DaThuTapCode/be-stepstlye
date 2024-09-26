package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.ChatLieuService;
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
}
