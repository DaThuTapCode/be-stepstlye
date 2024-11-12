package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaSearchRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
import com.okconde.bestepstyle.core.entity.PhieuGiamGia;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.phieugiamgia.request.PhieuGiamGiaRequestMapper;
import com.okconde.bestepstyle.core.mapper.phieugiamgia.response.PhieuGiamGiaResponseMapper;
import com.okconde.bestepstyle.core.repository.PhieuGiamGiaRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusPhieuGiamGia;
import com.okconde.bestepstyle.core.util.formater.DateFormater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TuanIf on 9/25/2024 20:28:25
 *
 * @author TuanIf
 */

@Service(value = "PhieuGiamGiaService")

public class PhieuGiamGiaService implements IBaseService<PhieuGiamGia, Long, PhieuGiamGiaRequest, PhieuGiamGiaResponse> {

    //Repo
    private final PhieuGiamGiaRepository phieuGiamGiaRepository;

    //Mapper
    private final PhieuGiamGiaRequestMapper phieuGiamGiaRequestMapper;
    private final PhieuGiamGiaResponseMapper phieuGiamGiaResponseMapper;

    public PhieuGiamGiaService(PhieuGiamGiaResponseMapper phieuGiamGiaResponseMapper, PhieuGiamGiaRepository phieuGiamGiaRepository, PhieuGiamGiaRequestMapper phieuGiamGiaRequestMapper) {
        this.phieuGiamGiaResponseMapper = phieuGiamGiaResponseMapper;
        this.phieuGiamGiaRepository = phieuGiamGiaRepository;
        this.phieuGiamGiaRequestMapper = phieuGiamGiaRequestMapper;
    }


    @Override
    public List<PhieuGiamGiaResponse> getPage(Pageable pageable) {
        Page<PhieuGiamGia> page = phieuGiamGiaRepository.findAll(pageable);
        return page.map(phieuGiamGiaResponseMapper::toDTO).getContent();
    }


    @Override
    public List<PhieuGiamGiaResponse> getAll() {
        List<PhieuGiamGia> phieuGiamGiaList = phieuGiamGiaRepository.findAll();
        return phieuGiamGiaResponseMapper.listToDTO(phieuGiamGiaList);
    }

    @Override
    @Transactional
    public PhieuGiamGiaResponse create(PhieuGiamGiaRequest phieuGiamGiaRequest) {
        // Kiểm tra ngày bắt đầu và ngày kết thúc có tồn tại
        if (phieuGiamGiaRequest.getNgayBatDau() == null || phieuGiamGiaRequest.getNgayKetThuc() == null) {
            throw new BusinessException("Ngày bắt đầu và ngày kết thúc không được để trống.");
        }

        // Chuyển đổi ngày bắt đầu và ngày kết thúc thành LocalDate để dễ dàng so sánh
        LocalDate ngayBatDau = phieuGiamGiaRequest.getNgayBatDau();
        LocalDate ngayKetThuc = phieuGiamGiaRequest.getNgayKetThuc();
        LocalDate today = LocalDate.now();

        // Kiểm tra ngày bắt đầu phải nhỏ hơn ngày kết thúc
        if (ngayBatDau.isAfter(ngayKetThuc)) {
            throw new BusinessException("Ngày bắt đầu phải nhỏ hơn ngày kết thúc.");
        }

        // Tự động đặt trạng thái dựa trên ngày bắt đầu
        if (ngayBatDau.isAfter(today)) {
            phieuGiamGiaRequest.setTrangThai(StatusPhieuGiamGia.COMINGSOON);
        } else if (ngayBatDau.isEqual(today)) {
            phieuGiamGiaRequest.setTrangThai(StatusPhieuGiamGia.ACTIVE);
        }


        phieuGiamGiaRequest.setMaPhieuGiamGia(GenerateCodeRandomUtil.generateProductCode("PGG", 7));

        return phieuGiamGiaResponseMapper.toDTO(phieuGiamGiaRepository.save(phieuGiamGiaRequestMapper.toEntity(phieuGiamGiaRequest)));
    }

    @Override
    @Transactional
    public PhieuGiamGiaResponse update(Long id, PhieuGiamGiaRequest phieuGiamGiaRequest) {
        PhieuGiamGia phieuGiamGiaExisting = phieuGiamGiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy với id: " + id));
        // Kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu
        if ((phieuGiamGiaRequest.getTrangThai() == StatusPhieuGiamGia.COMINGSOON ||
                phieuGiamGiaRequest.getTrangThai() == StatusPhieuGiamGia.ACTIVE) &&
                !phieuGiamGiaRequest.getNgayKetThuc().isAfter(phieuGiamGiaRequest.getNgayBatDau())) {
            throw new BusinessException("Ngày kết thúc phải lớn hơn ngày bắt đầu khi phiếu giảm giá đang ở trạng thái sắp diễn ra hoặc đang hoạt động.");
        }

        phieuGiamGiaExisting.setTenPhieuGiamGia(phieuGiamGiaRequest.getTenPhieuGiamGia());
        phieuGiamGiaExisting.setMoTa(phieuGiamGiaRequest.getMoTa());
        phieuGiamGiaExisting.setLoaiGiam(phieuGiamGiaRequest.getLoaiGiam());
        phieuGiamGiaExisting.setNgayBatDau(phieuGiamGiaRequest.getNgayBatDau());
        phieuGiamGiaExisting.setNgayKetThuc(phieuGiamGiaRequest.getNgayKetThuc());
        phieuGiamGiaExisting.setGiaTriGiamToiDa(phieuGiamGiaRequest.getGiaTriGiamToiDa());
        phieuGiamGiaExisting.setGiaTriGiam(phieuGiamGiaRequest.getGiaTriGiam());
        phieuGiamGiaExisting.setGiaTriHoaDonToiThieu(phieuGiamGiaRequest.getGiaTriHoaDonToiThieu());
        phieuGiamGiaExisting.setTrangThai(phieuGiamGiaRequest.getTrangThai());

        PhieuGiamGia phieuGiamGiaUpdated = phieuGiamGiaRepository.save(phieuGiamGiaExisting);
        return phieuGiamGiaResponseMapper.toDTO(phieuGiamGiaUpdated);
    }

    @Override
    public void delete(Long id) {

        PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Phiếu giảm giá không tồn tại"));
        phieuGiamGia.setTrangThai(StatusPhieuGiamGia.ACTIVE);
        phieuGiamGiaRepository.save(phieuGiamGia);
    }

    @Override
    public PhieuGiamGiaResponse getById(Long id) {
        PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy với id: " + id));
        return phieuGiamGiaResponseMapper.toDTO(phieuGiamGia);
    }

    public Page<PhieuGiamGiaResponse> searchPagePhieuGiamGia(Pageable pageable, PhieuGiamGiaSearchRequest phieuGiamGiaSearchRequest){
        Page<PhieuGiamGia> phieuGiamGiaPage =   phieuGiamGiaRepository.searchPagePhieuGiamGia(pageable,
                phieuGiamGiaSearchRequest.getMaPhieuGiamGia(),
                phieuGiamGiaSearchRequest.getTenPhieuGiamGia(),
                phieuGiamGiaSearchRequest.getNgayBatDau(),
                DateFormater.setEndDate(phieuGiamGiaSearchRequest.getNgayKetThuc()),
                phieuGiamGiaSearchRequest.getLoaiGiam(),
                phieuGiamGiaSearchRequest.getTrangThai());
        return phieuGiamGiaPage.map(phieuGiamGiaResponseMapper::toDTO);
    }

    public Map<String, Integer> getPhieuGiamGiaByStatus() {
        int activeCount = phieuGiamGiaRepository.countByStatus(StatusPhieuGiamGia.ACTIVE);
        int comingsoonCount = phieuGiamGiaRepository.countByStatus(StatusPhieuGiamGia.COMINGSOON);
        int expiredCount = phieuGiamGiaRepository.countByStatus(StatusPhieuGiamGia.EXPIRED);
        int cancelledCount = phieuGiamGiaRepository.countByStatus(StatusPhieuGiamGia.CANCELLED);

        //Tất cả PGG
        int totalCount = activeCount + comingsoonCount + expiredCount + cancelledCount;

        Map<String, Integer> counts = new HashMap<>();
        counts.put("TOTAL", totalCount);
        counts.put("ACTIVE", activeCount);
        counts.put("COMINGSOON", comingsoonCount);
        counts.put("EXPIRED", expiredCount);
        counts.put("CANCELLED", cancelledCount);

        return counts;
    }

    // Chạy mỗi ngày lúc 00:00 để kiểm tra và cập nhật trạng thái phiếu giảm giá
    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public List<PhieuGiamGiaResponse> expireCouponsIfNeeded() {
        LocalDate today = LocalDate.now();

        // Lấy danh sách phiếu giảm giá có trạng thái ACTIVE và ngày kết thúc trước ngày hiện tại
        List<PhieuGiamGia> expiringCoupons = phieuGiamGiaRepository.findByTrangThaiAndNgayKetThucBefore(StatusPhieuGiamGia.ACTIVE, today);

        // Cập nhật trạng thái của các phiếu giảm giá này thành EXPIRED
        expiringCoupons.forEach(coupon -> coupon.setTrangThai(StatusPhieuGiamGia.EXPIRED));

        // Lấy danh sách phiếu giảm giá có trạng thái COMINGSOON và ngày bắt đầu là ngày hiện taji
        List<PhieuGiamGia> activatingCoupons = phieuGiamGiaRepository.findByTrangThaiAndNgayBatDau(StatusPhieuGiamGia.COMINGSOON, today);

        // Cập nhật trạng thái của các phiếu giảm giá này thành ACTIVE
        activatingCoupons.forEach(coupon -> coupon.setTrangThai(StatusPhieuGiamGia.ACTIVE));

        // Lưu tất cả các phiếu giảm giá đã được cập nhật
        List<PhieuGiamGia> updatedCoupons = new ArrayList<>();
        updatedCoupons.addAll(expiringCoupons);
        updatedCoupons.addAll(activatingCoupons);
        phieuGiamGiaRepository.saveAll(updatedCoupons);

        // Chuyển đổi danh sách phiếu giảm giá đã cập nhật sang danh sách phản hồi
        return convertToResponseList(updatedCoupons);
    }

    // Phương thức chuyển đổi từ List<PhieuGiamGia> sang List<PhieuGiamGiaResponse>
    private List<PhieuGiamGiaResponse> convertToResponseList(List<PhieuGiamGia> coupons) {
        List<PhieuGiamGiaResponse> responseList = new ArrayList<>();
        for (PhieuGiamGia coupon : coupons) {
            PhieuGiamGiaResponse response = new PhieuGiamGiaResponse();
            response.setIdPhieuGiamGia(coupon.getIdPhieuGiamGia());
            response.setMaPhieuGiamGia(coupon.getMaPhieuGiamGia());
            response.setTenPhieuGiamGia(coupon.getTenPhieuGiamGia());
            response.setMoTa(coupon.getMoTa());
            response.setLoaiGiam(coupon.getLoaiGiam());
            response.setNgayBatDau(coupon.getNgayBatDau());
            response.setNgayKetThuc(coupon.getNgayKetThuc());
            response.setGiaTriGiamToiDa(coupon.getGiaTriGiamToiDa());
            response.setGiaTriHoaDonToiThieu(coupon.getGiaTriHoaDonToiThieu());
            response.setGiaTriGiam(coupon.getGiaTriGiam());
            response.setTrangThai(coupon.getTrangThai());

            responseList.add(response);
        }
        return responseList;
    }

    // Hàm kết thúc nhanh phiếu giảm giá
    @Transactional
    public PhieuGiamGiaResponse endPromotion(Long id) {
        PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu giảm giá với id: " + id));

        // Kiểm tra trạng thái phiếu giảm giá hiện tại
        if (phieuGiamGia.getTrangThai().equals(StatusPhieuGiamGia.ACTIVE)) {
            // Cập nhật trạng thái phiếu giảm giá thành "EXPIRED"
            phieuGiamGia.setTrangThai(StatusPhieuGiamGia.EXPIRED);
        } else if (phieuGiamGia.getTrangThai().equals(StatusPhieuGiamGia.COMINGSOON)) {
            // Cập nhật trạng thái phiếu giảm giá thành "CANCELLED"
            phieuGiamGia.setTrangThai(StatusPhieuGiamGia.CANCELLED);
        } else {
            throw new BusinessException("Chỉ kết thúc ở trạng thái ACTIVE và hủy ở trạng thái COMINGSOON");
        }

        PhieuGiamGia updatedPhieuGiamGia = phieuGiamGiaRepository.save(phieuGiamGia);
        return phieuGiamGiaResponseMapper.toDTO(updatedPhieuGiamGia);
    }




}
