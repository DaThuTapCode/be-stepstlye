package com.okconde.bestepstyle.feature.productmangement.service;

import com.okconde.bestepstyle.core.dto.danhmuc.request.DanhMucRequest;
import com.okconde.bestepstyle.core.dto.danhmuc.request.DanhMucSearchRequest;
import com.okconde.bestepstyle.core.dto.danhmuc.response.DanhMucResponse;
import com.okconde.bestepstyle.core.entity.DanhMuc;
import com.okconde.bestepstyle.core.exception.AttributeCodeDuplicateException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.danhmuc.request.DanhMucRequestMapper;
import com.okconde.bestepstyle.core.mapper.danhmuc.response.DanhMucResponseMapper;
import com.okconde.bestepstyle.core.repository.DanhMucRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 20:26
 *
 * @author Trong Phu
 */
@Service(value = "DanhMucService")
public class DanhMucService implements IBaseService <DanhMuc, Long, DanhMucRequest, DanhMucResponse>{

    private final DanhMucRepository danhMucRepository;

    private final DanhMucResponseMapper danhMucResponseMapper;
    private final DanhMucRequestMapper danhMucRequestMapper;

    public DanhMucService(
            DanhMucRepository danhMucRepository,
            DanhMucResponseMapper danhMucResponseMapper,
            DanhMucRequestMapper danhMucRequestMapper
    ) {
        this.danhMucRepository = danhMucRepository;
        this.danhMucResponseMapper = danhMucResponseMapper;
        this.danhMucRequestMapper = danhMucRequestMapper;
    }

    @Override
    public List<DanhMucResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<DanhMucResponse> getAll() {
        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        return danhMucResponseMapper.listToDTO(danhMucList);
    }

    @Override
    @Transactional
    public DanhMucResponse create(DanhMucRequest danhMucRequest) {
        danhMucRequest.setMaDanhMuc(GenerateCodeRandomUtil.generateProductCode("DM", 8));
        if (danhMucRepository.getDanhMucByTenDanhMuc(danhMucRequest.getTenDanhMuc()).isPresent()){
            throw new AttributeCodeDuplicateException("Tên danh mục " + danhMucRequest.getTenDanhMuc() + " đã tồn tại");
        }
        DanhMuc danhMucNew = danhMucRequestMapper.toEntity(danhMucRequest);
        danhMucNew.setNgayTao(LocalDateTime.now());
        danhMucNew.setNgayChinhSua(LocalDateTime.now());
        danhMucNew.setTrangThai(StatusEnum.ACTIVE);
        DanhMuc danhMucSaved = danhMucRepository.save(danhMucNew);
        return danhMucResponseMapper.toDTO(danhMucSaved);
    }

    @Override
    @Transactional
    public DanhMucResponse update(Long id, DanhMucRequest danhMucRequest) {
        DanhMuc danhMucExisting = danhMucRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục với id: " + id));

        DanhMuc danhMucToUpdate = danhMucRequestMapper.toEntity(danhMucRequest);
        danhMucExisting.setTenDanhMuc(danhMucToUpdate.getTenDanhMuc());
        danhMucExisting.setNgayChinhSua(LocalDateTime.now());
        danhMucExisting.setMoTa(danhMucToUpdate.getMoTa());

        DanhMuc danhMucUpdated = danhMucRepository.save(danhMucExisting);
        return danhMucResponseMapper.toDTO(danhMucUpdated);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public DanhMucResponse getById(Long aLong) {
        DanhMuc danhMuc = danhMucRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục với id: " +aLong));
        return danhMucResponseMapper.toDTO(danhMuc);
    }

    public Page<DanhMucResponse> searchPageDanhMuc(Pageable pageable, DanhMucSearchRequest danhMucSearchRequest){
        Page<DanhMuc> danhMucs = danhMucRepository.searchPageDanhMuc(pageable,
                danhMucSearchRequest.getMaDanhMuc(),
                danhMucSearchRequest.getTenDanhMuc()
        );
        return danhMucs.map(danhMucResponseMapper::toDTO);
    }
}
