package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.ThanhToan;
import com.okconde.bestepstyle.core.util.enumutil.StatusPTTT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by TuanIf on 10/16/2024 16:26:39
 *
 * @author TuanIf
 */
public interface ThanhToanRepository extends JpaRepository<ThanhToan, Long> {
    @Query("""
                select distinct tt from ThanhToan tt where (:maThanhToan is null or tt.maThanhToan = :maThanhToan)
                and (:phuongThucThanhToan is null or tt.phuongThucThanhToan = :phuongThucThanhToan) order by tt.idThanhToan desc
""")
    Page<ThanhToan> searchPageThanhToan(Pageable pageable,
                                  @Param(value = "maThanhToan") String maThanhToan,
                                  StatusPTTT phuongThucThanhToan
    );

    @Query("""
    select tt from  ThanhToan  tt where tt.phuongThucThanhToan = :phuongThucThanhToan
""")
    Optional<ThanhToan> findThanhToanByPhuongThucThanhToan (StatusPTTT phuongThucThanhToan);


}
