package com.okconde.bestepstyle.feature.employeemanagement.service;

import com.okconde.bestepstyle.core.dto.chucvu.request.ChucVuRequest;
import com.okconde.bestepstyle.core.dto.chucvu.response.ChucVuResponse;
import com.okconde.bestepstyle.core.entity.ChucVu;
import com.okconde.bestepstyle.core.mapper.chucvu.request.ChucVuRequestMapper;
import com.okconde.bestepstyle.core.mapper.chucvu.response.ChucVuResponseMapper;
import com.okconde.bestepstyle.core.repository.ChucVuRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Quang Minh on 9/25/2024 20:26:58
 *
 * @author Quang Minh
 */
@Service
public class ChucVuService implements IBaseService<ChucVu, Long, ChucVuRequest, ChucVuResponse> {

    private final ChucVuRepository chucVuRepository;

    private final ChucVuResponseMapper chucVuResponseMapper;

    private final ChucVuRequestMapper chucVuRequestMapper;

    public ChucVuService(
            ChucVuRepository chucVuRepository,
            ChucVuResponseMapper chucVuResponseMapper,
            ChucVuRequestMapper chucVuRequestMapper) {
        this.chucVuRepository = chucVuRepository;
        this.chucVuResponseMapper = chucVuResponseMapper;
        this.chucVuRequestMapper = chucVuRequestMapper;
    }


    @Override
    public List<ChucVuResponse> getPage(Pageable pageable) {

        return chucVuRepository.findAll(pageable).map(chucVuResponseMapper :: toDTO).getContent();

    }

    @Override
    public List<ChucVuResponse> getAll() {

        List<ChucVu> chucVuList = chucVuRepository.findAll();
        return chucVuResponseMapper.listToDTO(chucVuList);

    }

    @Override
    public ChucVuResponse create(ChucVuRequest chucVuRequest) {

        ChucVu chucVuMoi = chucVuRequestMapper.toEntity(chucVuRequest);
        ChucVu addCV = chucVuRepository.save(chucVuMoi);
        return chucVuResponseMapper.toDTO(addCV);

    }

    @Override
    public ChucVuResponse update(Long aLong, ChucVuRequest chucVuRequest) {
        // Tìm chức vụ theo id, nếu không tồn tại thì ném ngoại lệ
        ChucVu existingChucVu = chucVuRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Chức vụ không tồn tại"));

        // Cập nhật các trường từ request vào đối tượng chức vụ đã tìm thấy
        if (chucVuRequest.getTenChucVu() != null) {
            existingChucVu.setTenChucVu(chucVuRequest.getTenChucVu());
        }

        if (chucVuRequest.getMoTa() != null) {
            existingChucVu.setMoTa(chucVuRequest.getMoTa());
        }

        if (chucVuRequest.getTrangThai() != null) {
            existingChucVu.setTrangThai(chucVuRequest.getTrangThai());
        }

        // Lưu lại đối tượng chức vụ đã cập nhật vào cơ sở dữ liệu
        ChucVu updatedChucVu = chucVuRepository.save(existingChucVu);

        // Chuyển đổi từ Entity sang DTO để trả về
        return chucVuResponseMapper.toDTO(updatedChucVu);
    }


    @Override
    public void delete(Long aLong) {

        if (!chucVuRepository.existsById(aLong)){
            throw new IllegalArgumentException("Chức vụ không tồn tại");
        }
        chucVuRepository.deleteById(aLong);

    }

    @Override
    public ChucVuResponse getById(Long aLong) {

        ChucVu cv = chucVuRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Chức vụ không tồn tại"));
        return chucVuResponseMapper.toDTO(cv);

    }
}
