package com.okconde.bestepstyle.feature.employeemanagement.service;

import com.okconde.bestepstyle.core.dto.khachhang.request.KhachHangRequest;
import com.okconde.bestepstyle.core.dto.khachhang.request.KhachHangSearchRequest;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangResponse;
import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.exception.CustomerCodeDuplicateException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.khachhang.request.KhachHangRequestMapper;
import com.okconde.bestepstyle.core.mapper.khachhang.response.KhachHangResponseMapper;
import com.okconde.bestepstyle.core.repository.KhachHangRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Quang Minh on 9/25/2024 20:27:11
 *
 * @author Quang Minh
 */
@Service
public class KhachHangService implements IBaseService<KhachHang, Long, KhachHangRequest, KhachHangResponse> {

    private final KhachHangRepository khachHangRepository;

    private final KhachHangResponseMapper khachHangResponseMapper;

    private final KhachHangRequestMapper khachHangRequestMapper;

    public KhachHangService(
            KhachHangRepository khachHangRepository,
            KhachHangResponseMapper khachHangResponseMapper,
            KhachHangRequestMapper khachHangRequestMapper
    ) {
        this.khachHangRepository = khachHangRepository;
        this.khachHangResponseMapper = khachHangResponseMapper;
        this.khachHangRequestMapper = khachHangRequestMapper;
    }

    @Override
    public List<KhachHangResponse> getPage(Pageable pageable) {

        return khachHangRepository.findAll(pageable).map( khachHangResponseMapper :: toDTO).getContent();

    }

    @Override
    public List<KhachHangResponse> getAll() {

        List<KhachHang> lst = khachHangRepository.findAll();
        return khachHangResponseMapper.listToDTO(lst);

    }

    @Override
    @Transactional
    public KhachHangResponse create(KhachHangRequest khachHangRequest) {

        if (khachHangRepository.timKHTheoMaKH(khachHangRequest.getMaKhachHang()).isPresent()){
            throw new CustomerCodeDuplicateException("Mã khách hàng " + khachHangRequest.getMaKhachHang() + " đã tồn tại!");
        }
        KhachHang khachHangMoi = khachHangRequestMapper.toEntity(khachHangRequest);
        khachHangMoi.setNgayTao(LocalDateTime.now());
        khachHangMoi.setNgayChinhSua(LocalDateTime.now());
        KhachHang addKH = khachHangRepository.save(khachHangMoi);
        return khachHangResponseMapper.toDTO(addKH);

    }

    @Override
    @Transactional
    public KhachHangResponse update(Long aLong, KhachHangRequest khachHangRequest) {
        // Tìm khách hàng theo ID, nếu không tồn tại thì ném ra ngoại lệ
        KhachHang existingKH = khachHangRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Khách hàng không tồn tại!"));

//        existingKH.setTrangThai(StatusEnum.ACTIVE);

        // Cập nhật các trường từ request vào đối tượng khách hàng đã tìm thấy
        if (khachHangRequest.getMaKhachHang() != null) {
            existingKH.setMaKhachHang(khachHangRequest.getMaKhachHang());
        }

        if (khachHangRequest.getTenKhachHang() != null) {
            existingKH.setTenKhachHang(khachHangRequest.getTenKhachHang());
        }

        if (khachHangRequest.getSoDienThoai() != null) {
            existingKH.setSoDienThoai(khachHangRequest.getSoDienThoai());
        }

        if (khachHangRequest.getEmail() != null) {
            existingKH.setEmail(khachHangRequest.getEmail());
        }

        if (khachHangRequest.getNgaySinh() != null) {
            existingKH.setNgaySinh(khachHangRequest.getNgaySinh());
        }

        if (khachHangRequest.getGioiTinh() != null) {
            existingKH.setGioiTinh(khachHangRequest.getGioiTinh());
        }

        if (khachHangRequest.getGhiChu() != null) {
            existingKH.setGhiChu(khachHangRequest.getGhiChu());
        }

        existingKH.setTrangThai(khachHangRequest.getTrangThai());

        // Cập nhật thời gian chỉnh sửa cuối cùng
        existingKH.setNgayChinhSua(LocalDateTime.now());

        // Lưu lại đối tượng khách hàng đã cập nhật vào cơ sở dữ liệu
        KhachHang updatedKH = khachHangRepository.save(existingKH);

        // Chuyển đổi từ Entity sang DTO để trả về
        return khachHangResponseMapper.toDTO(updatedKH);
    }


    @Override
    @Transactional
    public void delete(Long aLong) {

        KhachHang kh = khachHangRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Khách hàng không tồn tại"));
        kh.setTrangThai(StatusEnum.INACTIVE);
        khachHangRepository.save(kh);
        
    }

    @Override
    public KhachHangResponse getById(Long aLong) {

        KhachHang kh = khachHangRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Khách hàng không tồn tại"));
        return khachHangResponseMapper.toDTO(kh);

    }

    public Page<KhachHangResponse> searchPageKH(Pageable pageable, KhachHangSearchRequest khachHangSearchRequest){

        Page<KhachHang> khachHangPage = khachHangRepository.searchPageKHByMaAndTenAndSDT
                (pageable, khachHangSearchRequest.getMaKhachHang(), khachHangSearchRequest.getTenKhachHang(), khachHangSearchRequest.getSoDienThoai());
        return khachHangPage.map(khachHangResponseMapper::toDTO);
    }
}
