package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonSearchRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.hoadon.request.HoaDonRequestMapper;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonResponseMapper;
import com.okconde.bestepstyle.core.repository.HoaDonRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import com.okconde.bestepstyle.core.util.formater.DateFormater;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by TuanIf on 9/25/2024 20:27:52
 *
 * @author TuanIf
 */

@Service(value = "HoaDonService")

public class HoaDonService implements IBaseService<HoaDon, Long, HoaDonRequest, HoaDonResponse> {
    //Repo
    private final HoaDonRepository hoaDonRepository;

    //Mapper
    private final HoaDonResponseMapper hoaDonResponseMapper;
    private final HoaDonRequestMapper hoaDonRequestMapper;

    public HoaDonService(HoaDonResponseMapper hoaDonResponseMapper, HoaDonRepository hoaDonRepository, HoaDonRequestMapper hoaDonRequestMapper) {
        this.hoaDonResponseMapper = hoaDonResponseMapper;
        this.hoaDonRepository = hoaDonRepository;
        this.hoaDonRequestMapper = hoaDonRequestMapper;
    }


    @Override
    public List<HoaDonResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<HoaDonResponse> getAll() {
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        return hoaDonResponseMapper.listToDTO(hoaDonList);
    }

    @Override
    @Transactional
    public HoaDonResponse create(HoaDonRequest hoaDonRequest) {

        hoaDonRequest.setMaHoaDon(GenerateCodeRandomUtil.generateProductCode("HD",8));
        hoaDonRequest.setTrangThai(StatusHoaDon.PAID);

        return hoaDonResponseMapper.toDTO(hoaDonRepository.save(hoaDonRequestMapper.toEntity(hoaDonRequest)));
    }

    @Override
    @Transactional
    public HoaDonResponse update(Long id, HoaDonRequest hoaDonRequest) {
        HoaDon hoaDonExisting = hoaDonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy với id: " + id));

        hoaDonExisting.setNgayTaoDon(LocalDateTime.now());
        hoaDonExisting.setPhiVanChuyen(hoaDonRequest.getPhiVanChuyen());
        hoaDonExisting.setTongTien(hoaDonRequest.getTongTien());
        hoaDonExisting.setTongTienSauGiam(hoaDonRequest.getTongTienSauGiam());
        hoaDonExisting.setNgayChinhSua(LocalDateTime.now());
        hoaDonExisting.setNgayXacNhan(LocalDateTime.now());
        hoaDonExisting.setNgayNhanHang(LocalDateTime.now());
        hoaDonExisting.setLoaiHoaDon(hoaDonRequest.getLoaiHoaDon());
        hoaDonExisting.setTenKhachHang(hoaDonRequest.getTenKhachHang());
        hoaDonExisting.setDiaChiGiaoHang(hoaDonRequest.getDiaChiGiaoHang());
        hoaDonExisting.setSoDienThoaiKhachHang(hoaDonRequest.getSoDienThoaiKhachHang());
        hoaDonExisting.setGhiChu(hoaDonRequest.getGhiChu());
        hoaDonExisting.setTrangThai(hoaDonRequest.getTrangThai());

        HoaDon hoaDonUpdated = hoaDonRepository.save(hoaDonExisting);
        return hoaDonResponseMapper.toDTO(hoaDonUpdated);
    }

    @Override
    public void delete(Long id) {

        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hóa đơn không tồn tại"));
                hoaDon.setTrangThai(StatusHoaDon.PAID);
                hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDonResponse getById(Long id) {
        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id: " + id));
        return hoaDonResponseMapper.toDTO(hoaDon);
    }

    public Page<HoaDonResponse> searchPageHoaDon(Pageable pageable, HoaDonSearchRequest hoaDonSearchRequest){
        Page<HoaDon> hoaDonPage = hoaDonRepository.searchPageHoaDon(pageable,
                hoaDonSearchRequest.getMaHoaDon(),
                hoaDonSearchRequest.getNgayTaoStart(),
                DateFormater.setEndDate(hoaDonSearchRequest.getNgayTaoEnd()),
                hoaDonSearchRequest.getTenKhachHang(),
                hoaDonSearchRequest.getSoDienThoai(),
                hoaDonSearchRequest.getTrangThai()
                );
        return hoaDonPage.map(hoaDonResponseMapper::toDTO);
    }

    public Map<String, Integer> getHoaDonByStatus() {
            int pendingCount = hoaDonRepository.countByStatus(StatusHoaDon.PENDING);
            int shippingCount = hoaDonRepository.countByStatus(StatusHoaDon.SHIPPING);
            int paidCount = hoaDonRepository.countByStatus(StatusHoaDon.PAID);
            int cancelledCount = hoaDonRepository.countByStatus(StatusHoaDon.CANCELLED);
            int refundedCount = hoaDonRepository.countByStatus(StatusHoaDon.REFUNDED);
            int overdueCount = hoaDonRepository.countByStatus(StatusHoaDon.OVERDUE);

            Map<String, Integer> counts = new HashMap<>();
            counts.put("PENDING", pendingCount);
            counts.put("SHIPPING", shippingCount);
            counts.put("PAID", paidCount);
            counts.put("CANCELLED", cancelledCount);
            counts.put("REFUNDED", refundedCount);
            counts.put("OVERDUE", overdueCount);

            return counts;
    }
}
