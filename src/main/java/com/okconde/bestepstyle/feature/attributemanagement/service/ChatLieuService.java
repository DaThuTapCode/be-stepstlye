package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuRequest;
import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.entity.ChatLieu;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.mapper.chatlieu.response.ChatLieuResponseMapper;
import com.okconde.bestepstyle.core.repository.ChatLieuRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.feature.attributemanagement.controller.ChatLieuController;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Service
public class ChatLieuService implements IBaseService<ChatLieu, Long, ChatLieuRequest, ChatLieuResponse> {
    private final ChatLieuRepository chatLieuRepository;

    private final ChatLieuResponseMapper chatLieuResponseMapper;

    public ChatLieuService(
            ChatLieuRepository chatLieuRepository,
            ChatLieuResponseMapper chatLieuResponseMapper
    ) {
        this.chatLieuRepository = chatLieuRepository;
        this.chatLieuResponseMapper = chatLieuResponseMapper;
    }


    @Override
    public List<ChatLieuResponse> getPage(Pageable pageable) {
        List<ChatLieu> list = chatLieuRepository.findAll(pageable).getContent();
        return chatLieuResponseMapper.listToDTO(list);
    }

    @Override
    public List<ChatLieuResponse> getAll() {
        List<ChatLieu> chatLieuList = chatLieuRepository.findAll();
        return chatLieuResponseMapper.listToDTO(chatLieuList);
    }

    @Override
    public ChatLieuResponse create(ChatLieuRequest chatLieuRequest) {
        ChatLieu chatLieu = new ChatLieu();
        chatLieu.setTenChatLieu(chatLieuRequest.getTenChatLieu());
        chatLieu.setDoBen(chatLieuRequest.getDoBen());
        chatLieu.setMoTa(chatLieuRequest.getMoTa());
        chatLieu.setTrangThai(chatLieu.getTrangThai());
        ChatLieu savecl = chatLieuRepository.save(chatLieu);
        return chatLieuResponseMapper.toDTO(savecl);
    }

    @Override
    public ChatLieuResponse update(Long aLong, ChatLieuRequest chatLieuRequest) {
        ChatLieu chatLieu = chatLieuRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Chất liệu không tồn tại"));
        chatLieu.setTenChatLieu(chatLieuRequest.getTenChatLieu());
        chatLieu.setDoBen(chatLieuRequest.getDoBen());
        chatLieu.setMoTa(chatLieuRequest.getMoTa());
        chatLieu.setTrangThai(chatLieu.getTrangThai());
        ChatLieu updatecl = chatLieuRepository.save(chatLieu);
        return chatLieuResponseMapper.toDTO(updatecl);
    }

    @Override
    public void delete(Long aLong) {
        ChatLieu chatLieu = chatLieuRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Chất liệu không tồn tại"));
        chatLieu.setDeleted(true);  // Đánh dấu đã xóa
        chatLieuRepository.save(chatLieu);
        System.out.println("Đã xóa mềm chất liệu với ID: " + aLong);
    }

    @Override
    public ChatLieuResponse getById(Long aLong) {
        ChatLieu chatLieu = chatLieuRepository.findById(aLong).orElseThrow(() ->
                new IllegalArgumentException("Chất liệu không tồn tại id"));
        return chatLieuResponseMapper.toDTO(chatLieu);
    }

}
