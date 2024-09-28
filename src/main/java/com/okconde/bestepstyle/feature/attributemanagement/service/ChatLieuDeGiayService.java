package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.chatlieudegiay.request.ChatLieuDeGiayRequest;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.response.ChatLieuDeGiayResponse;
import com.okconde.bestepstyle.core.entity.ChatLieuDeGiay;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.mapper.chatlieudegiay.response.ChatLieuDeGiayResponseMapper;
import com.okconde.bestepstyle.core.repository.ChatLieuDeGiayRepository;
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
        ChatLieuDeGiay cldg = new ChatLieuDeGiay();
        cldg.setTenChatLieuDeGiay(chatLieuDeGiayRequest.getTenChatLieuDeGiay());
        cldg.setGiaTri(chatLieuDeGiayRequest.getGiaTri());
        cldg.setMoTa(chatLieuDeGiayRequest.getMoTa());
        cldg.setTrangThai(chatLieuDeGiayRequest.getTrangThai());
        ChatLieuDeGiay savecldg = chatLieuDeGiayRepository.save(cldg);
        return chatLieuDeGiayResponseMapper.toDTO(savecldg);
    }

    @Override
    public ChatLieuDeGiayResponse update(Long aLong, ChatLieuDeGiayRequest chatLieuDeGiayRequest) {
        ChatLieuDeGiay cldg = chatLieuDeGiayRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Chất liệu để giày không tồn tại"));
        cldg.setTenChatLieuDeGiay(chatLieuDeGiayRequest.getTenChatLieuDeGiay());
        cldg.setGiaTri(chatLieuDeGiayRequest.getGiaTri());
        cldg.setMoTa(chatLieuDeGiayRequest.getMoTa());
        cldg.setTrangThai(chatLieuDeGiayRequest.getTrangThai());
        ChatLieuDeGiay updatecldg = chatLieuDeGiayRepository.save(cldg);
        return chatLieuDeGiayResponseMapper.toDTO(updatecldg);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public ChatLieuDeGiayResponse getById(Long aLong) {
        ChatLieuDeGiay cldg = chatLieuDeGiayRepository.findById(aLong).orElseThrow(() ->
                new IllegalArgumentException("Chất liệu để giày không tồn tại id"));
        return chatLieuDeGiayResponseMapper.toDTO(cldg);
    }
}
