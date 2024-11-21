package com.okconde.bestepstyle.feature.productmangement.service;

import com.okconde.bestepstyle.core.dto.danhmuc.request.DanhMucSearchRequest;
import com.okconde.bestepstyle.core.dto.danhmuc.response.DanhMucResponse;
import com.okconde.bestepstyle.core.dto.thuonghieu.request.ThuongHieuRequest;
import com.okconde.bestepstyle.core.dto.thuonghieu.request.ThuongHieuSearchRequest;
import com.okconde.bestepstyle.core.dto.thuonghieu.response.ThuongHieuResponse;
import com.okconde.bestepstyle.core.entity.DanhMuc;
import com.okconde.bestepstyle.core.entity.ThuongHieu;
import com.okconde.bestepstyle.core.exception.AttributeCodeDuplicateException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.thuonghieu.request.ThuongHieuRequestMapper;
import com.okconde.bestepstyle.core.mapper.thuonghieu.response.ThuongHieuResponseMapper;
import com.okconde.bestepstyle.core.repository.ThuongHieuRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 20:27
 *
 * @author Trong Phu
 */
@Service(value = "ThuongHieuService")
public class ThuongHieuService implements IBaseService<ThuongHieu, Long, ThuongHieuRequest, ThuongHieuResponse> {

    //Repo
    private final ThuongHieuRepository thuongHieuRepository;

    //mapper
    private final ThuongHieuResponseMapper thuongHieuResponseMapper;
    private final ThuongHieuRequestMapper thuongHieuRequestMapper;

    //Constructor
    public ThuongHieuService(
            ThuongHieuRepository thuongHieuRepository,
            ThuongHieuResponseMapper thuongHieuResponseMapper,
            ThuongHieuRequestMapper thuongHieuRequestMapper
    ) {
        this.thuongHieuRepository = thuongHieuRepository;
        this.thuongHieuResponseMapper = thuongHieuResponseMapper;
        this.thuongHieuRequestMapper = thuongHieuRequestMapper;
    }


    @Override
    public List<ThuongHieuResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<ThuongHieuResponse> getAll() {
        return thuongHieuResponseMapper.listToDTO(thuongHieuRepository.findAll());
    }

    @Override
    public ThuongHieuResponse create(ThuongHieuRequest thuongHieuRequest) {
        thuongHieuRequest.setMaThuongHieu(GenerateCodeRandomUtil.generateProductCode("TH", 8));
        if (thuongHieuRepository.getThuongHieuByTenThuongHieu(thuongHieuRequest.getTenThuongHieu()).isPresent()){
            throw new AttributeCodeDuplicateException("Tên thương hiệu " + thuongHieuRequest.getTenThuongHieu() + " đã tồn tại");
        }
        ThuongHieu thuongHieuNew = thuongHieuRequestMapper.toEntity(thuongHieuRequest);
        thuongHieuNew.setTrangThai(StatusEnum.ACTIVE);
        ThuongHieu thuongHieuSaved = thuongHieuRepository.save(thuongHieuNew);
        return thuongHieuResponseMapper.toDTO(thuongHieuSaved);
    }

    @Override
    public ThuongHieuResponse update(Long id, ThuongHieuRequest thuongHieuRequest) {
        ThuongHieu thuongHieuExisting = thuongHieuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thương hiệu với id: " + id));
        ThuongHieu thuongHieuToUpdate = thuongHieuRequestMapper.toEntity(thuongHieuRequest);

        thuongHieuExisting.setTenThuongHieu(thuongHieuToUpdate.getTenThuongHieu());
        thuongHieuExisting.setXuatXu(thuongHieuToUpdate.getXuatXu());
        thuongHieuExisting.setMoTa(thuongHieuToUpdate.getMoTa());

        ThuongHieu thuongHieuUpdated = thuongHieuRepository.save(thuongHieuExisting);
        return thuongHieuResponseMapper.toDTO(thuongHieuUpdated);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public ThuongHieuResponse getById(Long aLong) {
        ThuongHieu thuongHieu = thuongHieuRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thương hiệu với id: " + aLong));
        return thuongHieuResponseMapper.toDTO(thuongHieu);
    }

    public Page<ThuongHieuResponse> searchPageThuongHieu(Pageable pageable, ThuongHieuSearchRequest thuongHieuSearchRequest){
        Page<ThuongHieu> thuongHieus = thuongHieuRepository.searchPageThuongHieu(pageable,
                thuongHieuSearchRequest.getMaThuongHieu(),
                thuongHieuSearchRequest.getTenThuongHieu()
        );
        return thuongHieus.map(thuongHieuResponseMapper::toDTO);
    }
}
