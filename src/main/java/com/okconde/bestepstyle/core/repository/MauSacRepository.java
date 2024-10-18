package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.MauSac;
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
public interface MauSacRepository extends JpaRepository<MauSac, Long> {

    @Query("""
            select distinct ms from MauSac ms 
            where (:maMauSac is null or ms.maMauSac like %:maMauSac%)
            and (:tenMau is null or ms.tenMau like %:tenMau%)
            """)
    Page<MauSac> searchPageMauSac(Pageable pageable,
                                  @Param("maMauSac") String maMauSac,
                                  @Param("tenMau") String tenMau
    );
    @Query("""
        select ms from MauSac ms where ms.maMauSac = :maMau
    """)
    Optional<MauSac> getMauSacByMaMau(String maMau);
}
