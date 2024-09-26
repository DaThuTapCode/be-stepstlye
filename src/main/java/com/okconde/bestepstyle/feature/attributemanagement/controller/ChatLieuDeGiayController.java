package com.okconde.bestepstyle.feature.attributemanagement.controller;

import com.okconde.bestepstyle.core.dto.chatlieudegiay.response.ChatLieuDeGiayResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.attributemanagement.service.ChatLieuDeGiayService;
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
}
