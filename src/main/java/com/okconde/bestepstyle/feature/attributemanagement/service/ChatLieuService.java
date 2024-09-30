package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuRequest;
import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.entity.ChatLieu;
import com.okconde.bestepstyle.core.entity.KichCo;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.mapper.chatlieu.response.ChatLieuResponseMapper;
import com.okconde.bestepstyle.core.repository.ChatLieuRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.feature.attributemanagement.controller.ChatLieuController;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        ChatLieu entity = chatLieuResponseMapper.toEntity(chatLieuRequest);
        ChatLieu chatLieu = chatLieuRepository.save(entity);
        return chatLieuResponseMapper.toDTO(chatLieu);
    }

    @Override
    public ChatLieuResponse update(Long aLong, ChatLieuRequest chatLieuRequest) {
        Optional<ChatLieu> optionalChatLieu = chatLieuRepository.findById(aLong);
        if(optionalChatLieu.isPresent() && !optionalChatLieu.get().isDeleted()) {
            ChatLieu chatLieu = optionalChatLieu.get();
            // Update các trường từ Resquet
            chatLieu = chatLieuResponseMapper.toEntity(chatLieuRequest);
            chatLieu.setIdChatLieu(aLong);
            chatLieu = chatLieuRepository.save(chatLieu);
            return chatLieuResponseMapper.toDTO(chatLieu);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id" + aLong);
        }
    }

    @Override
    public void delete(Long aLong) {
        Optional<ChatLieu> optionalChatLieu = chatLieuRepository.findById(aLong);
        if (optionalChatLieu.isPresent()){
            ChatLieu chatLieu = optionalChatLieu.get();
            chatLieu.setDeleted(true);
            chatLieuRepository.save(chatLieu);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + aLong);
        }
    }

    @Override
    public ChatLieuResponse getById(Long aLong) {
        ChatLieu chatLieu = chatLieuRepository.findById(aLong).orElseThrow(() ->
                new IllegalArgumentException("Chất liệu không tồn tại id"));
        return chatLieuResponseMapper.toDTO(chatLieu);
    }

}
