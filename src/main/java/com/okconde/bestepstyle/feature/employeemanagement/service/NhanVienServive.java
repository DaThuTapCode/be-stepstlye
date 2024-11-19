package com.okconde.bestepstyle.feature.employeemanagement.service;

import com.okconde.bestepstyle.core.dto.nhanvien.request.NhanVienRequest;
import com.okconde.bestepstyle.core.dto.nhanvien.request.NhanVienSearchRequest;
import com.okconde.bestepstyle.core.dto.nhanvien.response.NhanVienResponse;
import com.okconde.bestepstyle.core.entity.ChucVu;
import com.okconde.bestepstyle.core.entity.NhanVien;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.exception.CustomerCodeDuplicateException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.nhanvien.request.NhanVienRequestMapper;
import com.okconde.bestepstyle.core.mapper.nhanvien.response.NhanVienResponseMapper;
import com.okconde.bestepstyle.core.repository.NhanVienRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Quang Minh on 9/25/2024 20:26:41
 *
 * @author Quang Minh
 */
@Service
public class NhanVienServive implements IBaseService<NhanVien, Long, NhanVienRequest, NhanVienResponse> {

    private final NhanVienRepository nhanVienRepository;

    private final NhanVienResponseMapper nhanVienResponseMapper;

    private final NhanVienRequestMapper nhanVienRequestMapper;

    public NhanVienServive(
            NhanVienRepository nhanVienRepository,
            NhanVienResponseMapper nhanVienResponseMapper,
            NhanVienRequestMapper nhanVienRequestMapper) {
        this.nhanVienRepository = nhanVienRepository;
        this.nhanVienResponseMapper = nhanVienResponseMapper;
        this.nhanVienRequestMapper = nhanVienRequestMapper;
    }

    @Override
    public List<NhanVienResponse> getPage(Pageable pageable) {

        return nhanVienRepository.findAll(pageable).map(nhanVienResponseMapper :: toDTO).getContent();
    }

    @Override
    public List<NhanVienResponse> getAll() {

        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        return nhanVienResponseMapper.listToDTO(nhanVienList);

    }

    @Override
    @Transactional
    public NhanVienResponse create(NhanVienRequest nhanVienRequest) {
//        nhanVienRequest.setMaNhanVien(GenerateCodeRandomUtil.generateProductCode("NV", 8));
        if (nhanVienRepository.timNVTheoMaNV(nhanVienRequest.getMaNhanVien()).isPresent()){
            throw new CustomerCodeDuplicateException("Mã nhân viên " + nhanVienRequest.getMaNhanVien() + " đã tồn tại!");
        }
        NhanVien nhanVienMoi = nhanVienRequestMapper.toEntity(nhanVienRequest);
        nhanVienMoi.setNgayTao(LocalDateTime.now());
        nhanVienMoi.setNgayChinhSua(LocalDateTime.now());
        ChucVu cv = new ChucVu();
        cv.setIdChucVu(2L);
        nhanVienMoi.setChucVu(cv);
        NhanVien addNV = nhanVienRepository.save(nhanVienMoi);
        return nhanVienResponseMapper.toDTO(addNV);

    }

    @Override
    @Transactional
    public NhanVienResponse update(Long aLong, NhanVienRequest nhanVienRequest) {

        // Tìm kiếm nhân viên dựa trên id, nếu không tồn tại, ném ngoại lệ
        NhanVien existingNhanVien = nhanVienRepository.findById(aLong)
                .orElseThrow(() -> new BusinessException("Nhân viên không tồn tại"));

        // Cập nhật các trường từ request
        if (nhanVienRequest.getHoTen() != null) {
            existingNhanVien.setHoTen(nhanVienRequest.getHoTen());
        }

        if (nhanVienRequest.getNgaySinh() != null) {
            existingNhanVien.setNgaySinh(nhanVienRequest.getNgaySinh());
        }

        if (nhanVienRequest.getDiaChi() != null) {
            existingNhanVien.setDiaChi(nhanVienRequest.getDiaChi());
        }

        if (nhanVienRequest.getGioiTinh() != null) {
            existingNhanVien.setGioiTinh(nhanVienRequest.getGioiTinh());
        }

        if (nhanVienRequest.getSoDienThoai() != null) {
            existingNhanVien.setSoDienThoai(nhanVienRequest.getSoDienThoai());
        }

        if (nhanVienRequest.getEmail() != null) {
            existingNhanVien.setEmail(nhanVienRequest.getEmail());
        }

        if (nhanVienRequest.getGhiChu() != null) {
            existingNhanVien.setGhiChu(nhanVienRequest.getGhiChu());
        }

        if (nhanVienRequest.getAnh() != null) {
            existingNhanVien.setAnh(nhanVienRequest.getAnh());
        }

        if (nhanVienRequest.getTrangThai() != null) {
            existingNhanVien.setTrangThai(nhanVienRequest.getTrangThai());
        }

        // Cập nhật NhanVien nếu có trong request
        existingNhanVien.setNgayChinhSua(LocalDateTime.now());

        // Lưu nhân viên đã cập nhật vào database
        NhanVien updatedNhanVien = nhanVienRepository.save(existingNhanVien);

        // Chuyển đổi Entity sang DTO và trả về
        return nhanVienResponseMapper.toDTO(updatedNhanVien);

    }

    @Override
    @Transactional
    public void delete(Long aLong) {

        NhanVien nv = nhanVienRepository.findById(aLong)
                .orElseThrow(() -> new BusinessException("Nhân viên không tồn tại"));
        nv.setTrangThai(StatusEnum.INACTIVE);
        nhanVienRepository.save(nv);

    }

    @Override
    public NhanVienResponse getById(Long aLong) {

        NhanVien nv = nhanVienRepository.findById(aLong)
                .orElseThrow(() -> new BusinessException("Nhân viên không tồn tại"));
        return nhanVienResponseMapper.toDTO(nv);

    }

    public Page<NhanVienResponse> searchPageNV(Pageable pageable, NhanVienSearchRequest nhanVienSearchRequest){

        Page<NhanVien> nhanVienPage = nhanVienRepository.searchPageNVByMaAndTenAndSDT
                (pageable, nhanVienSearchRequest.getMaNhanVien(),
                        nhanVienSearchRequest.getHoTen(),
                        nhanVienSearchRequest.getSoDienThoai());
        return nhanVienPage.map(nhanVienResponseMapper::toDTO);
    }
}
