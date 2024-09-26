package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuRequest;
import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.entity.ChatLieu;
import com.okconde.bestepstyle.core.mapper.chatlieu.response.ChatLieuResponseMapper;
import com.okconde.bestepstyle.core.repository.ChatLieuRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
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
        return null;
    }

    @Override
    public List<ChatLieuResponse> getAll() {
        List<ChatLieu> chatLieuList = chatLieuRepository.findAll();
        return chatLieuResponseMapper.listToDTO(chatLieuList);
    }

    @Override
    public ChatLieuResponse create(ChatLieuRequest chatLieuRequest) {
        return null;
    }

    @Override
    public ChatLieuResponse update(Long aLong, ChatLieuRequest chatLieuRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public ChatLieuResponse getById(Long aLong) {
        return null;
    }
}
