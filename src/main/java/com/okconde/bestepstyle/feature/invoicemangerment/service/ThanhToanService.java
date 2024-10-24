package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.thanhtoan.request.ThanhToanRequest;
import com.okconde.bestepstyle.core.dto.thanhtoan.request.ThanhToanSearchRequest;
import com.okconde.bestepstyle.core.dto.thanhtoan.response.ThanhToanResponse;
import com.okconde.bestepstyle.core.entity.ThanhToan;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.thanhtoan.request.ThanhToanRequestMapper;
import com.okconde.bestepstyle.core.mapper.thanhtoan.response.ThanhToanResponseMapper;
import com.okconde.bestepstyle.core.repository.ThanhToanRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by TuanIf on 10/16/2024 16:28:19
 *
 * @author TuanIf
 */

@Service(value = "ThanhToanService")

public class ThanhToanService implements IBaseService<ThanhToan, Long, ThanhToanRequest, ThanhToanResponse> {
    //Repository
    private final ThanhToanRepository thanhToanRepository;

    //Mapper
    private final ThanhToanResponseMapper thanhToanResponseMapper;
    private final ThanhToanRequestMapper thanhToanRequestMapper;

    public ThanhToanService(ThanhToanRepository thanhToanRepository, ThanhToanResponseMapper thanhToanResponseMapper, ThanhToanRequestMapper thanhToanRequestMapper) {
        this.thanhToanRepository = thanhToanRepository;
        this.thanhToanResponseMapper = thanhToanResponseMapper;
        this.thanhToanRequestMapper = thanhToanRequestMapper;
    }

    @Override
    public List<ThanhToanResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<ThanhToanResponse> getAll() {
        List<ThanhToan> thanhToanList = thanhToanRepository.findAll();
        return thanhToanResponseMapper.listToDTO(thanhToanList);
    }

    @Override
    public ThanhToanResponse create(ThanhToanRequest thanhToanRequest) {
        ThanhToan thanhToanNew = thanhToanRequestMapper.toEntity(thanhToanRequest);
        thanhToanNew.setMaThanhToan(thanhToanNew.getMaThanhToan());
        thanhToanNew.setPhuongThucThanhToan(thanhToanNew.getPhuongThucThanhToan());
        thanhToanNew.setTrangThai(StatusEnum.ACTIVE);

        ThanhToan thanhToanSaved = thanhToanRepository.save(thanhToanNew);
        return thanhToanResponseMapper.toDTO(thanhToanSaved);
    }

    @Override
    public ThanhToanResponse update(Long id, ThanhToanRequest thanhToanRequest) {
        ThanhToan thanhToanExisting = thanhToanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy với id: " + id));

        thanhToanExisting.setMaThanhToan(thanhToanRequest.getMaThanhToan());
        thanhToanExisting.setPhuongThucThanhToan(thanhToanRequest.getPhuongThucThanhToan());
        thanhToanExisting.setTrangThai(thanhToanRequest.getTrangThai());

        ThanhToan thanhToanUpdated = thanhToanRepository.save(thanhToanExisting);
        return thanhToanResponseMapper.toDTO(thanhToanUpdated);
    }

    @Override
    public void delete(Long id) {

        ThanhToan thanhToan = thanhToanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Thanh toán không tồn tại"));
        thanhToan.setTrangThai(StatusEnum.ACTIVE);
        thanhToanRepository.save(thanhToan);
    }

    @Override
    public ThanhToanResponse getById(Long id) {
        ThanhToan thanhToan = thanhToanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id: " +id));
        return thanhToanResponseMapper.toDTO(thanhToan);
    }

    public Page<ThanhToanResponse> searchPageThanhToan(Pageable pageable, ThanhToanSearchRequest thanhToanSearchRequest){
        Page<ThanhToan> thanhToanPage = thanhToanRepository.searchPageThanhToan(pageable, thanhToanSearchRequest.getMaThanhToan(), thanhToanSearchRequest.getPhuongThucThanhToan());
        return thanhToanPage.map(thanhToanResponseMapper::toDTO);
    }
}
