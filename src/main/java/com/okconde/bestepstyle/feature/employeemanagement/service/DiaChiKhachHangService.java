package com.okconde.bestepstyle.feature.employeemanagement.service;

import com.okconde.bestepstyle.core.dto.diachikhachhang.request.DiaChiKhachHangRequest;
import com.okconde.bestepstyle.core.dto.diachikhachhang.response.DiaChiKhachHangResponse;
import com.okconde.bestepstyle.core.entity.DiaChiKhachHang;
import com.okconde.bestepstyle.core.mapper.diachikhachhang.request.DiaChiKhachHangRequestMapper;
import com.okconde.bestepstyle.core.mapper.diachikhachhang.response.DiaChiKhachHangResponseMapper;
import com.okconde.bestepstyle.core.repository.DiaChiKhachHangRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    private final DiaChiKhachHangResponseMapper diaChiKhachHangResponseMapper;

    private final DiaChiKhachHangRequestMapper diaChiKhachHangRequestMapper;

    public DiaChiKhachHangService(DiaChiKhachHangRepository diaChiKhachHangRepository,
                                  DiaChiKhachHangResponseMapper diaChiKhachHangResponseMapper,
                                  DiaChiKhachHangRequestMapper diaChiKhachHangRequestMapper) {
        this.diaChiKhachHangRepository = diaChiKhachHangRepository;
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
    public DiaChiKhachHangResponse create(DiaChiKhachHangRequest diaChiKhachHangRequest) {

        DiaChiKhachHang dckhMoi = diaChiKhachHangRequestMapper.toEntity(diaChiKhachHangRequest);
        DiaChiKhachHang addDCKH = diaChiKhachHangRepository.save(dckhMoi);
        return diaChiKhachHangResponseMapper.toDTO(addDCKH);

    }

    @Override
    public DiaChiKhachHangResponse update(Long aLong, DiaChiKhachHangRequest diaChiKhachHangRequest) {
        // Tìm địa chỉ khách hàng theo id, nếu không tồn tại thì ném ngoại lệ
        DiaChiKhachHang existingDCKH = diaChiKhachHangRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Địa chỉ khách hàng không tồn tại"));

        // Cập nhật các trường từ request vào đối tượng địa chỉ khách hàng đã tìm thấy
        if (diaChiKhachHangRequest.getKhachHang() != null) {
            existingDCKH.setKhachHang(diaChiKhachHangRequest.getKhachHang());
        }

        if (diaChiKhachHangRequest.getQuocGia() != null) {
            existingDCKH.setQuocGia(diaChiKhachHangRequest.getQuocGia());
        }

        if (diaChiKhachHangRequest.getThanhPho() != null) {
            existingDCKH.setThanhPho(diaChiKhachHangRequest.getThanhPho());
        }

        if (diaChiKhachHangRequest.getHuyen() != null) {
            existingDCKH.setHuyen(diaChiKhachHangRequest.getHuyen());
        }

        if (diaChiKhachHangRequest.getXa() != null) {
            existingDCKH.setXa(diaChiKhachHangRequest.getXa());
        }

        if (diaChiKhachHangRequest.getDuong() != null) {
            existingDCKH.setDuong(diaChiKhachHangRequest.getDuong());
        }

        if (diaChiKhachHangRequest.getSoNha() != null) {
            existingDCKH.setSoNha(diaChiKhachHangRequest.getSoNha());
        }

        // Lưu lại đối tượng địa chỉ khách hàng đã cập nhật vào cơ sở dữ liệu
        DiaChiKhachHang updatedDCKH = diaChiKhachHangRepository.save(existingDCKH);

        // Chuyển đổi từ Entity sang DTO để trả về
        return diaChiKhachHangResponseMapper.toDTO(updatedDCKH);
    }


    @Override
    public void delete(Long aLong) {

        if (!diaChiKhachHangRepository.existsById(aLong)){
            throw new IllegalArgumentException("Địa chỉ khách hàng không tồn tại");
        }
        diaChiKhachHangRepository.deleteById(aLong);

    }

    @Override
    public DiaChiKhachHangResponse getById(Long aLong) {

        DiaChiKhachHang dckh = diaChiKhachHangRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Địa chi khách hàng không tồn tại"));
        return diaChiKhachHangResponseMapper.toDTO(dckh);

    }
}
