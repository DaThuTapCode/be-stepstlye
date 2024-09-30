package com.okconde.bestepstyle.feature.employeemanagement.service;

import com.okconde.bestepstyle.core.dto.khachhang.request.KhachHangRequest;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangResponse;
import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.mapper.khachhang.request.KhachHangRequestMapper;
import com.okconde.bestepstyle.core.mapper.khachhang.response.KhachHangResponseMapper;
import com.okconde.bestepstyle.core.repository.KhachHangRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public KhachHangResponse create(KhachHangRequest khachHangRequest) {

        KhachHang khachHangMoi = khachHangRequestMapper.toEntity(khachHangRequest);
        khachHangMoi.setNgayChinhSua(LocalDateTime.now());
        KhachHang addKH = khachHangRepository.save(khachHangMoi);
        return khachHangResponseMapper.toDTO(addKH);

    }

    @Override
    public KhachHangResponse update(Long aLong, KhachHangRequest khachHangRequest) {
        // Tìm khách hàng theo ID, nếu không tồn tại thì ném ra ngoại lệ
        KhachHang existingKH = khachHangRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Khách hàng không tồn tại"));

        // Cập nhật các trường từ request vào đối tượng khách hàng đã tìm thấy
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

        // Cập nhật thời gian chỉnh sửa cuối cùng
        existingKH.setNgayChinhSua(LocalDateTime.now());

        // Lưu lại đối tượng khách hàng đã cập nhật vào cơ sở dữ liệu
        KhachHang updatedKH = khachHangRepository.save(existingKH);

        // Chuyển đổi từ Entity sang DTO để trả về
        return khachHangResponseMapper.toDTO(updatedKH);
    }


    @Override
    public void delete(Long aLong) {

        if (!khachHangRepository.existsById(aLong)){
            throw new IllegalArgumentException("Khách hàng không tồn tại");
        }
        khachHangRepository.deleteById(aLong);
    }

    @Override
    public KhachHangResponse getById(Long aLong) {

        KhachHang kh = khachHangRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Khách hàng không tồn tại"));
        return khachHangResponseMapper.toDTO(kh);

    }
}
