package com.okconde.bestepstyle.core.dto.chatlieu.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatLieuRequest {

    private String tenChatLieu;

    private String doBen;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    private boolean deleted = false;
}