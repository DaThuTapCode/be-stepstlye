package com.okconde.bestepstyle.core.dto.chatlieu.response;

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
public class ChatLieuResponse {

    private Long idChatLieu;

    private String tenChatLieu;

    private String doBen;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}