package com.okconde.bestepstyle.core.dto.chatlieudegiay.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Created at 17/10/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatLieuDeGiaySearchRequest {
    private String maChatLieuDeGiay;

    private String tenChatLieuDeGiay;
}
