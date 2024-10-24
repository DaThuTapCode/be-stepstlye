package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Created by Quang Minh on 9/23/2024 22:11:04
 *
 * @author Quang Minh
 */
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {

    @Query("""
                select distinct nv from NhanVien nv where (:maNV is null or nv.maNhanVien like %:maNV%)
                and (:tenNV is null or nv.hoTen like %:tenNV%)
                and (:SDT is null or nv.soDienThoai like %:SDT%) order by nv.idNhanVien DESC
""")
    Page<NhanVien> searchPageNVByMaAndTenAndSDT(Pageable pageable, String maNV, String tenNV, String SDT);

    @Query("""
                select nv from NhanVien nv where nv.maNhanVien like :maNV
""")
    Optional<NhanVien> timNVTheoMaNV(String maNV);
}
