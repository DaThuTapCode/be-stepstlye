package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.entity.TrongLuong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created at 23/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
public interface TrongLuongRepository extends JpaRepository<TrongLuong, Long> {
    @Query("""
            select distinct tl from TrongLuong tl 
            where (:maTrongLuong is null or tl.maTrongLuong like %:maTrongLuong%)
            and (:giaTri is null or tl.giaTri like %:giaTri%) order by tl.idTrongLuong desc 
            """)
    Page<TrongLuong> searchPageTrongLuong(Pageable pageable,
                                  @Param("maTrongLuong") String maTrongLuong,
                                  @Param("giaTri") String giaTri
    );
    @Query("""
        select tl from TrongLuong tl where tl.maTrongLuong = :maTrongLuong
    """)
    Optional<TrongLuong> getTrongLuongByMaTrongLuong(String maTrongLuong);
}
