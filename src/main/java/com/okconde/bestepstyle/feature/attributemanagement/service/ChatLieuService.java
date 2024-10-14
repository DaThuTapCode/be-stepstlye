package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuRequest;
import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.entity.*;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.chatlieu.request.ChatLieuRequestMapper;
import com.okconde.bestepstyle.core.mapper.chatlieu.response.ChatLieuResponseMapper;
import com.okconde.bestepstyle.core.repository.ChatLieuRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.feature.attributemanagement.controller.ChatLieuController;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    private final ChatLieuRequestMapper chatLieuRequestMapper;

    public ChatLieuService(
            ChatLieuRepository chatLieuRepository,
            ChatLieuResponseMapper chatLieuResponseMapper,
            ChatLieuRequestMapper chatLieuRequestMapper
    ) {
        this.chatLieuRepository = chatLieuRepository;
        this.chatLieuResponseMapper = chatLieuResponseMapper;
        this.chatLieuRequestMapper = chatLieuRequestMapper;
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
    @Transactional
    public ChatLieuResponse create(ChatLieuRequest chatLieuRequest) {
        ChatLieu chatLieu = chatLieuRequestMapper.toEntity(chatLieuRequest);
        chatLieu.setTrangThai(StatusEnum.ACTIVE);
        ChatLieu chatLieuSave = chatLieuRepository.save(chatLieu);
        return chatLieuResponseMapper.toDTO(chatLieuSave);
    }

    @Override
    @Transactional
    public ChatLieuResponse update(Long id, ChatLieuRequest chatLieuRequest) {
        ChatLieu chatLieu = chatLieuRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Không tìm thấy chất liệu với id" + id));

        chatLieu.setMaChatLieu(chatLieuRequest.getTenChatLieu());
        chatLieu.setTenChatLieu(chatLieuRequest.getTenChatLieu());
        chatLieu.setDoBen(chatLieuRequest.getDoBen());
        chatLieu.setMoTa(chatLieuRequest.getMoTa());
        chatLieu.setTrangThai(chatLieuRequest.getTrangThai());
        ChatLieu chatLieuUpdated = chatLieuRepository.save(chatLieu);
        return chatLieuResponseMapper.toDTO(chatLieuUpdated);
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        Optional<ChatLieu> optionalChatLieu = chatLieuRepository.findById(aLong);
        if (optionalChatLieu.isPresent()){
            ChatLieu chatLieu = optionalChatLieu.get();
            chatLieu.setTrangThai(StatusEnum.INACTIVE);
            chatLieuRepository.save(chatLieu);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + aLong);
        }
    }

    @Override
    public ChatLieuResponse getById(Long aLong) {
        ChatLieu chatLieu = chatLieuRepository.findById(aLong).orElseThrow(() ->
                new ResourceNotFoundException("Chất liệu không tồn tại id"));
        return chatLieuResponseMapper.toDTO(chatLieu);
    }

}
