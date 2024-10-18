package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.ThanhToan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by TuanIf on 10/16/2024 16:26:39
 *
 * @author TuanIf
 */
public interface ThanhToanRepository extends JpaRepository<ThanhToan, Long> {
    @Query("""
                select distinct tt from ThanhToan tt where (:maThanhToan is null or tt.maThanhToan = :maThanhToan)
                and (:phuongThucThanhToan is null or tt.phuongThucThanhToan = :phuongThucThanhToan)
""")
    Page<ThanhToan> searchPageThanhToan(Pageable pageable,
                                  @Param(value = "maThanhToan") String maThanhToan,
                                  @Param(value = "phuongThucThanhToan") String phuongThucThanhToan
    );
}
