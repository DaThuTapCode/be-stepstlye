package com.okconde.bestepstyle.core.mapper.chatlieudegiay.response;

import com.okconde.bestepstyle.core.dto.chatlieudegiay.response.ChatLieuDeGiayResponse;
import com.okconde.bestepstyle.core.entity.ChatLieuDeGiay;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Mapper(componentModel = "spring")
public interface ChatLieuDeGiayResponseMapper extends IBaseMapper<ChatLieuDeGiay, ChatLieuDeGiayResponse> {
}
