package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
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
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {
    @Query("""
                            select distinct spct from SanPhamChiTiet spct where 
                            (:maSpct is null or spct.maSpct = :maSpct)
                            and
                            (:idMauSac is null or spct.mauSac.idMauSac = :idMauSac)
                            and 
                            (:idChatLieu is null or spct.chatLieu.idChatLieu = :idChatLieu)
                            and 
                            (:idChatLieuDeGiay is null or spct.chatLieuDeGiay.idChatLieuDeGiay = :idChatLieuDeGiay)
                            and 
                            (:idKieuDeGiay is null or spct.kieuDeGiay.idKieuDeGiay = :idKieuDeGiay)
                            and 
                            (:idKichCo is null or spct.kichCo.idKichCo = :idKichCo)
                            and 
                            (:idTrongLuong is null or spct.trongLuong.idTrongLuong = :idTrongLuong)
            """)
    Page<SanPhamChiTiet> searchPageSPCT(Pageable pageable,
                                  @Param(value = "maSpct") String maSpct,
                                  @Param(value = "idMauSac") Long idMauSac,
                                  @Param(value = "idChatLieu") Long idChatLieu,
                                        @Param(value = "idChatLieuDeGiay") Long idChatLieuDeGiay,
                                        @Param(value = "idKieuDeGiay") Long idKieuDeGiay,
                                        @Param(value = "idKichCo") Long idKichCo,
                                        @Param(value = "idTrongLuong") Long idTrongLuong
                                  );

}


