package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.ChatLieu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Created at 23/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
public interface ChatLieuRepository extends JpaRepository<ChatLieu, Long> {
    @Query("""
            select distinct cl from ChatLieu cl
            where (:maChatLieu is null or cl.maChatLieu like %:maChatLieu%)
            and (:tenChatLieu is null or cl.tenChatLieu like %:tenChatLieu%)
            """)
    Page<ChatLieu> searchPageChatLieu(Pageable pageable,
                                      @Param("maChatLieu") String maChatLieu,
                                      @Param("tenChatLieu") String tenChatLieu
    );
}
