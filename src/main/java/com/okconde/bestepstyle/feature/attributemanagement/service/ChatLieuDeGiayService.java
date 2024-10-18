package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.chatlieudegiay.request.ChatLieuDeGiayRequest;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.request.ChatLieuDeGiaySearchRequest;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.response.ChatLieuDeGiayResponse;
import com.okconde.bestepstyle.core.entity.ChatLieuDeGiay;
import com.okconde.bestepstyle.core.entity.DanhMuc;
import com.okconde.bestepstyle.core.entity.KichCo;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.chatlieudegiay.request.ChatLieuDeGiayRequestMapper;
import com.okconde.bestepstyle.core.mapper.chatlieudegiay.response.ChatLieuDeGiayResponseMapper;
import com.okconde.bestepstyle.core.repository.ChatLieuDeGiayRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final ChatLieuDeGiayRequestMapper chatLieuDeGiayRequestMapper;

    public ChatLieuDeGiayService(
            ChatLieuDeGiayRepository chatLieuDeGiayRepository,
            ChatLieuDeGiayResponseMapper chatLieuDeGiayResponseMapper,
            ChatLieuDeGiayRequestMapper chatLieuDeGiayRequestMapper
    ) {
        this.chatLieuDeGiayRepository = chatLieuDeGiayRepository;
        this.chatLieuDeGiayResponseMapper = chatLieuDeGiayResponseMapper;
        this.chatLieuDeGiayRequestMapper = chatLieuDeGiayRequestMapper;
    }

    @Override
    public List<ChatLieuDeGiayResponse> getPage(Pageable pageable) {
        return chatLieuDeGiayRepository.findAll(pageable).map(chatLieuDeGiayResponseMapper ::toDTO).getContent();

    }

    @Override
    public List<ChatLieuDeGiayResponse> getAll() {
        List<ChatLieuDeGiay> chatLieuDeGiayList = chatLieuDeGiayRepository.findAll();
        return chatLieuDeGiayResponseMapper.listToDTO(chatLieuDeGiayList);
    }

    @Override
    @Transactional
    public ChatLieuDeGiayResponse create(ChatLieuDeGiayRequest chatLieuDeGiayRequest) {
        ChatLieuDeGiay entity = chatLieuDeGiayRequestMapper.toEntity(chatLieuDeGiayRequest);
        entity.setTrangThai(StatusEnum.ACTIVE);
        ChatLieuDeGiay chatLieuDeGiay = chatLieuDeGiayRepository.save(entity);
        return chatLieuDeGiayResponseMapper.toDTO(chatLieuDeGiay);
    }

    @Override
    @Transactional
    public ChatLieuDeGiayResponse update(Long id, ChatLieuDeGiayRequest chatLieuDeGiayRequest) {
        ChatLieuDeGiay chatLieuDeGiay = chatLieuDeGiayRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Không tìm thấy chất liệu sản phẩm với id" + id));


        chatLieuDeGiay.setMaChatLieuDeGiay(chatLieuDeGiayRequest.getMaChatLieuDeGiay());
        chatLieuDeGiay.setTenChatLieuDeGiay(chatLieuDeGiayRequest.getTenChatLieuDeGiay());
        chatLieuDeGiay.setGiaTri(chatLieuDeGiayRequest.getGiaTri());
        chatLieuDeGiay.setMoTa(chatLieuDeGiayRequest.getMoTa());
        chatLieuDeGiay.setTrangThai(chatLieuDeGiayRequest.getTrangThai());
        ChatLieuDeGiay chatLieuDeGiayUpdated = chatLieuDeGiayRepository.save(chatLieuDeGiay);
        return chatLieuDeGiayResponseMapper.toDTO(chatLieuDeGiayUpdated);
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        Optional<ChatLieuDeGiay> optionalChatLieuDeGiay = chatLieuDeGiayRepository.findById(aLong);
        if (optionalChatLieuDeGiay.isPresent()){
            ChatLieuDeGiay chatLieuDeGiay = optionalChatLieuDeGiay.get();
            chatLieuDeGiay.setTrangThai(StatusEnum.INACTIVE);
            chatLieuDeGiayRepository.save(chatLieuDeGiay);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + aLong);
        }
    }

    @Override
    public ChatLieuDeGiayResponse getById(Long aLong) {
        ChatLieuDeGiay cldg = chatLieuDeGiayRepository.findById(aLong).orElseThrow(() ->
                new ResourceNotFoundException("Chất liệu để giày không tồn tại id"));
        return chatLieuDeGiayResponseMapper.toDTO(cldg);
    }

    public Page<ChatLieuDeGiayResponse> searchPageChatLieuDeGiay(Pageable pageable, ChatLieuDeGiaySearchRequest chatLieuDeGiaySearchRequest){
        Page<ChatLieuDeGiay> chatLieuDeGiayPage = chatLieuDeGiayRepository.searchPageChatLieuDeGiay(pageable,
                chatLieuDeGiaySearchRequest.getMaChatLieuDeGiay(),
                chatLieuDeGiaySearchRequest.getTenChatLieuDeGiay()
        );
        return chatLieuDeGiayPage.map(chatLieuDeGiayResponseMapper::toDTO);
    }
}
