package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.ChatLieu;
import com.okconde.bestepstyle.core.entity.DanhMuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Trong Phu on 23/09/2024 22:00
 *
 * @author Trong Phu
 */
public interface DanhMucRepository extends JpaRepository<DanhMuc, Long> {
    @Query("""
            select distinct dm from DanhMuc dm
            where (:maDanhMuc is null or dm.maDanhMuc like %:maDanhMuc%)
            and (:tenDanhMuc is null or dm.tenDanhMuc like %:tenDanhMuc%) order by dm.idDanhMuc desc 
            """)
    Page<DanhMuc> searchPageDanhMuc(Pageable pageable,
                                      @Param("maDanhMuc") String maDanhMuc,
                                      @Param("tenDanhMuc") String tenDanhMuc
    );
}
