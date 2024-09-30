package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.chatlieudegiay.request.ChatLieuDeGiayRequest;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.response.ChatLieuDeGiayResponse;
import com.okconde.bestepstyle.core.entity.ChatLieu;
import com.okconde.bestepstyle.core.entity.ChatLieuDeGiay;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.mapper.chatlieudegiay.response.ChatLieuDeGiayResponseMapper;
import com.okconde.bestepstyle.core.repository.ChatLieuDeGiayRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
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
public class ChatLieuDeGiayService implements IBaseService<ChatLieuDeGiay, Long, ChatLieuDeGiayRequest, ChatLieuDeGiayResponse> {

    private final ChatLieuDeGiayRepository chatLieuDeGiayRepository;

    private final ChatLieuDeGiayResponseMapper chatLieuDeGiayResponseMapper;

    public ChatLieuDeGiayService(
            ChatLieuDeGiayRepository chatLieuDeGiayRepository,
            ChatLieuDeGiayResponseMapper chatLieuDeGiayResponseMapper
    ) {
        this.chatLieuDeGiayRepository = chatLieuDeGiayRepository;
        this.chatLieuDeGiayResponseMapper = chatLieuDeGiayResponseMapper;
    }

    @Override
    public List<ChatLieuDeGiayResponse> getPage(Pageable pageable) {
        List<ChatLieuDeGiay> chatLieuDeGiayList = chatLieuDeGiayRepository.findAll(pageable).getContent();
        return chatLieuDeGiayResponseMapper.listToDTO(chatLieuDeGiayList);
    }

    @Override
    public List<ChatLieuDeGiayResponse> getAll() {
        List<ChatLieuDeGiay> chatLieuDeGiayList = chatLieuDeGiayRepository.findAll();
        return chatLieuDeGiayResponseMapper.listToDTO(chatLieuDeGiayList);
    }

    @Override
    public ChatLieuDeGiayResponse create(ChatLieuDeGiayRequest chatLieuDeGiayRequest) {
        ChatLieuDeGiay entity = chatLieuDeGiayResponseMapper.toEntity(chatLieuDeGiayRequest);
        ChatLieuDeGiay chatLieuDeGiay = chatLieuDeGiayRepository.save(entity);
        return chatLieuDeGiayResponseMapper.toDTO(chatLieuDeGiay);
    }

    @Override
    public ChatLieuDeGiayResponse update(Long aLong, ChatLieuDeGiayRequest chatLieuDeGiayRequest) {
        Optional<ChatLieuDeGiay> optionalChatLieuDeGiay = chatLieuDeGiayRepository.findById(aLong);
        if(optionalChatLieuDeGiay.isPresent() && !optionalChatLieuDeGiay.get().isDeleted()) {
            ChatLieuDeGiay chatLieuDeGiay = optionalChatLieuDeGiay.get();
            // Update các trường từ Resquet
            chatLieuDeGiay = chatLieuDeGiayResponseMapper.toEntity(chatLieuDeGiayRequest);
            chatLieuDeGiay.setIdChatLieuDeGiay(aLong);
            chatLieuDeGiay = chatLieuDeGiayRepository.save(chatLieuDeGiay);
            return chatLieuDeGiayResponseMapper.toDTO(chatLieuDeGiay);
        } else {
            throw new EntityNotFoundException("Không tìm thấy id" + aLong);
        }
    }

    @Override
    public void delete(Long aLong) {
        Optional<ChatLieuDeGiay> optionalChatLieuDeGiay = chatLieuDeGiayRepository.findById(aLong);
        if (optionalChatLieuDeGiay.isPresent()){
            ChatLieuDeGiay chatLieuDeGiay = optionalChatLieuDeGiay.get();
            chatLieuDeGiay.setDeleted(true);
            chatLieuDeGiayRepository.save(chatLieuDeGiay);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + aLong);
        }
    }

    @Override
    public ChatLieuDeGiayResponse getById(Long aLong) {
        ChatLieuDeGiay cldg = chatLieuDeGiayRepository.findById(aLong).orElseThrow(() ->
                new IllegalArgumentException("Chất liệu để giày không tồn tại id"));
        return chatLieuDeGiayResponseMapper.toDTO(cldg);
    }
}
