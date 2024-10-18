package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Created by Quang Minh on 9/23/2024 22:10:35
 *
 * @author Quang Minh
 */
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {

    @Query("""
                select distinct kh from KhachHang kh where (:maKH is null or kh.maKhachHang like %:maKH%)
                and (:tenKH is null or kh.tenKhachHang like %:tenKH%)
                and (:SDT is null or kh.soDienThoai like %:SDT%)
""")
    Page<KhachHang> searchPageKHByMaAndTenAndSDT(Pageable pageable, String maKH, String tenKH, String SDT);

    @Query("""
                select kh from KhachHang kh where kh.maKhachHang like :maKH
""")
    Optional<KhachHang> timKHTheoMaKH(String maKH);
}
