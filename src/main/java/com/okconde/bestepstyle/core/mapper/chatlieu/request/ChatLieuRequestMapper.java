package com.okconde.bestepstyle.core.mapper.chatlieu.request;

import com.okconde.bestepstyle.core.dto.chatlieu.request.ChatLieuRequest;
import com.okconde.bestepstyle.core.entity.ChatLieu;
import com.okconde.bestepstyle.core.mapper.IBaseMapper;
import org.mapstruct.Mapper;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Mapper(componentModel = "spring")
public interface ChatLieuRequestMapper extends IBaseMapper<ChatLieu, ChatLieuRequest> {
}
