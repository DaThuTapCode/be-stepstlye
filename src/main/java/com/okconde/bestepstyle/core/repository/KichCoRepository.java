package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.KichCo;
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
public interface KichCoRepository extends JpaRepository<KichCo, Long> {
    @Query("""
        select distinct kc from KichCo kc
        where (:maKichCo is null or kc.maKichCo like %:maKichCo%)
        and (:giaTri is null or kc.giaTri = :giaTri)
        """)
    Page<KichCo> searchPageKichCo(Pageable pageable,
                                  @Param("maKichCo") String maKichCo,
                                  @Param("giaTri") Double giaTri
    );

}
