package com.okconde.bestepstyle.feature.dashboardmanagement.service;

import com.okconde.bestepstyle.core.dto.dashboard.response.DoanhThuNgayResponseDTO;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.repository.HoaDonRepository;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Trong Phu on 28/11/2024 23:34
 *
 * @author Trong Phu
 */
@Service
public class DashboardService implements IDashboardService {

    private final HoaDonRepository hoaDonRepository;

    public DashboardService(HoaDonRepository hoaDonRepository) {
        this.hoaDonRepository = hoaDonRepository;
    }

    /**
     * @return
     */
    @Override
    public BigDecimal layDoanhThuNgay() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        List<HoaDon> danhSachHoaDon = hoaDonRepository.layHoaDonTheoTrangThaiVaNgay(StatusHoaDon.PAID, startOfDay, endOfDay);
        return danhSachHoaDon.stream()
                .map(hd -> hd.getTongTienSauGiam().add(hd.getPhiVanChuyen())) // Cộng tiền sau giảm và phí vận chuyển
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Tính tổng
    }

    /**
     * @return
     */
    @Override
    public BigDecimal layDoanhThuThangHienTai() {
        LocalDateTime startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1);

        List<HoaDon> danhSachHoaDon = hoaDonRepository.layHoaDonTheoTrangThaiVaThang(StatusHoaDon.PAID, startOfMonth, endOfMonth);
        return danhSachHoaDon.stream()
                .map(hd -> hd.getTongTienSauGiam().add(hd.getPhiVanChuyen()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * @return
     */
    @Override
    public BigDecimal layDoanhThuNamHienTai() {
        LocalDateTime startOfYear = LocalDate.now().withDayOfYear(1).atStartOfDay();
        LocalDateTime endOfYear = startOfYear.plusYears(1);

        List<HoaDon> danhSachHoaDon = hoaDonRepository.layHoaDonTheoTrangThaiVaNam(StatusHoaDon.PAID, startOfYear, endOfYear);
        return danhSachHoaDon.stream()
                .map(hd -> hd.getTongTienSauGiam().add(hd.getPhiVanChuyen()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * @param thang
     * @param nam
     * @return
     */
    @Override
    public List<DoanhThuNgayResponseDTO> layDoanhThuCacNgayTheoThangVaNam(Integer thang, Integer nam) {
        LocalDateTime startOfMonth = LocalDateTime.of(nam, thang, 1, 0, 0);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1);

        // Lấy dữ liệu doanh thu từ database
        List<Object[]> rawData = hoaDonRepository.layDoanhThuTheoTungNgayTrongThang(StatusHoaDon.PAID, startOfMonth, endOfMonth);

        // Tạo danh sách tất cả các ngày trong tháng
        List<DoanhThuNgayResponseDTO> result = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            final int finalI = i;  // Gán vào biến final
            Optional<Object[]> dayData = rawData.stream()
                    .filter(record -> ((Number) record[0]).intValue() == finalI)
                    .findFirst();

            if (dayData.isPresent()) {
                result.add(new DoanhThuNgayResponseDTO(finalI, new BigDecimal(dayData.get()[1].toString())));
            } else {
                result.add(new DoanhThuNgayResponseDTO(finalI, BigDecimal.ZERO));
            }
        }
        return result;
    }


}
