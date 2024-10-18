package com.okconde.bestepstyle.core.dto.chatlieu.request;

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
public class ChatLieuSearchRequest {
    private String maChatLieu;

    private String tenChatLieu;
}
