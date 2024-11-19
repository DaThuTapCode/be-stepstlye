package com.okconde.bestepstyle.feature.employeemanagement.service;

import com.okconde.bestepstyle.core.dto.diachikhachhang.request.DiaChiKhachHangRequest;
import com.okconde.bestepstyle.core.dto.diachikhachhang.response.DiaChiKhachHangResponse;
import com.okconde.bestepstyle.core.entity.DiaChiKhachHang;
import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.diachikhachhang.request.DiaChiKhachHangRequestMapper;
import com.okconde.bestepstyle.core.mapper.diachikhachhang.response.DiaChiKhachHangResponseMapper;
import com.okconde.bestepstyle.core.repository.DiaChiKhachHangRepository;
import com.okconde.bestepstyle.core.repository.KhachHangRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Quang Minh on 9/25/2024 20:27:27
 *
 * @author Quang Minh
 */
@Service
public class DiaChiKhachHangService implements IBaseService<DiaChiKhachHang, Long,
        DiaChiKhachHangRequest, DiaChiKhachHangResponse> {

    private final DiaChiKhachHangRepository diaChiKhachHangRepository;

    private final KhachHangRepository khachHangRepository;

    private final DiaChiKhachHangResponseMapper diaChiKhachHangResponseMapper;

    private final DiaChiKhachHangRequestMapper diaChiKhachHangRequestMapper;

    public DiaChiKhachHangService(DiaChiKhachHangRepository diaChiKhachHangRepository, KhachHangRepository khachHangRepository,
                                  DiaChiKhachHangResponseMapper diaChiKhachHangResponseMapper,
                                  DiaChiKhachHangRequestMapper diaChiKhachHangRequestMapper) {
        this.diaChiKhachHangRepository = diaChiKhachHangRepository;
        this.khachHangRepository = khachHangRepository;
        this.diaChiKhachHangResponseMapper = diaChiKhachHangResponseMapper;
        this.diaChiKhachHangRequestMapper = diaChiKhachHangRequestMapper;
    }

    @Override
    public List<DiaChiKhachHangResponse> getPage(Pageable pageable) {

        return diaChiKhachHangRepository.findAll(pageable).map(diaChiKhachHangResponseMapper :: toDTO).getContent();

    }

    @Override
    public List<DiaChiKhachHangResponse> getAll() {

        List<DiaChiKhachHang> listDCKH = diaChiKhachHangRepository.findAll();
        return diaChiKhachHangResponseMapper.listToDTO(listDCKH);

    }

    @Override
    @Transactional
    public DiaChiKhachHangResponse create(DiaChiKhachHangRequest diaChiKhachHangRequest) {

        DiaChiKhachHang dckhMoi = diaChiKhachHangRequestMapper.toEntity(diaChiKhachHangRequest);
        DiaChiKhachHang addDCKH = diaChiKhachHangRepository.save(dckhMoi);
        return diaChiKhachHangResponseMapper.toDTO(addDCKH);

    }

    @Override
    @Transactional
    public DiaChiKhachHangResponse update(Long aLong, DiaChiKhachHangRequest diaChiKhachHangRequest) {
        // Tìm địa chỉ khách hàng theo id, nếu không tồn tại thì ném ngoại lệ
        DiaChiKhachHang existingDCKH = diaChiKhachHangRepository.findById(aLong)
                .orElseThrow(() -> new BusinessException("Địa chỉ khách hàng không tồn tại"));

        // Cập nhật các trường từ request vào đối tượng địa chỉ khách hàng đã tìm thấy
        if (diaChiKhachHangRequest.getKhachHang() != null) {
            existingDCKH.setKhachHang(diaChiKhachHangRequest.getKhachHang());
        }

        // Lưu lại đối tượng địa chỉ khách hàng đã cập nhật vào cơ sở dữ liệu
        DiaChiKhachHang updatedDCKH = diaChiKhachHangRepository.save(existingDCKH);

        // Chuyển đổi từ Entity sang DTO để trả về
        return diaChiKhachHangResponseMapper.toDTO(updatedDCKH);
    }


    @Override
    @Transactional
    public void delete(Long aLong) {

        DiaChiKhachHang dckh = diaChiKhachHangRepository.findById(aLong)
                .orElseThrow(() -> new BusinessException("Địa chi khách hàng không tồn tại"));
        dckh.setTrangThai(StatusEnum.INACTIVE);
        diaChiKhachHangRepository.save(dckh);

    }

    @Override
    public DiaChiKhachHangResponse getById(Long aLong) {

        DiaChiKhachHang dckh = diaChiKhachHangRepository.findById(aLong)
                .orElseThrow(() -> new BusinessException("Địa chi khách hàng không tồn tại"));
        return diaChiKhachHangResponseMapper.toDTO(dckh);

    }

    @Transactional
    public DiaChiKhachHangResponse createDCKHByIdKH(Long idKH, DiaChiKhachHangRequest diaChiKhachHangRequest){

        KhachHang kh = khachHangRepository.timKHTheoIDVaTrangThai(idKH, StatusEnum.ACTIVE)
                .orElseThrow(() -> new BusinessException("Khách hàng không tồn tại"));
        DiaChiKhachHang dckh = diaChiKhachHangRequestMapper.toEntity(diaChiKhachHangRequest);
        dckh.setMaDiaChiKhachHang(GenerateCodeRandomUtil.generateProductCode("DCKH", 6));
        dckh.setKhachHang(kh);
        List<DiaChiKhachHang> diaChiKhachHangList = kh.getDiaChiKhachHangs();
        diaChiKhachHangList.add(diaChiKhachHangRepository.save(dckh));
        return null;
    }
}
