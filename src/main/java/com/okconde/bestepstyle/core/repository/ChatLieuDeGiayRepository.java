package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.ChatLieuDeGiay;

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
public interface ChatLieuDeGiayRepository extends JpaRepository<ChatLieuDeGiay, Long> {
    @Query("""
            select distinct cldg from ChatLieuDeGiay cldg
            where (:maChatLieuDeGiay is null or cldg.maChatLieuDeGiay like %:maChatLieuDeGiay%)
            and (:tenChatLieuDeGiay is null or cldg.tenChatLieuDeGiay like %:tenChatLieuDeGiay%)
            """)
    Page<ChatLieuDeGiay> searchPageChatLieuDeGiay(Pageable pageable,
                                                  @Param("maChatLieuDeGiay") String maChatLieuDeGiay,
                                                  @Param("tenChatLieuDeGiay") String tenChatLieuDeGiay
    );
}
