package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.ChatLieu;
import com.okconde.bestepstyle.core.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Trong Phu on 23/09/2024 22:01
 *
 * @author Trong Phu
 */
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Long> {
    @Query("""
            select distinct th from ThuongHieu th
            where (:maThuongHieu is null or th.maThuongHieu like %:maThuongHieu%)
            and (:tenThuongHieu is null or th.tenThuongHieu like %:tenThuongHieu%) order by th.idThuongHieu desc 
            """)
    Page<ThuongHieu> searchPageThuongHieu(Pageable pageable,
                                      @Param("maThuongHieu") String maThuongHieu,
                                      @Param("tenThuongHieu") String tenThuongHieu
    );
}
