package com.okconde.bestepstyle.feature.attributemanagement.service;

import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.dto.kichco.request.KichCoRequest;
import com.okconde.bestepstyle.core.dto.kichco.request.KichCoSearchRequest;
import com.okconde.bestepstyle.core.entity.KichCo;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.exception.AttributeCodeDuplicateException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.kichco.request.KichCoRequestMapper;
import com.okconde.bestepstyle.core.mapper.kichco.response.KichCoResponseMapper;
import com.okconde.bestepstyle.core.repository.KichCoRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Service
public class KichCoService implements IBaseService<KichCo, Long, KichCoRequest, KichCoResponse> {
    private final KichCoRepository kichCoRepository;

    private final KichCoResponseMapper kichCoResponseMapper;

    private final KichCoRequestMapper kichCoRequestMapper;

    public KichCoService(KichCoRepository kichCoRepository,
                         KichCoResponseMapper kichCoResponseMapper,
                         KichCoRequestMapper kichCoRequestMapper
    ) {
        this.kichCoRepository = kichCoRepository;
        this.kichCoResponseMapper = kichCoResponseMapper;
        this.kichCoRequestMapper = kichCoRequestMapper;
    }


    @Override
    public List<KichCoResponse> getPage(Pageable pageable) {
        return kichCoRepository.findAll(pageable).map(kichCoResponseMapper ::toDTO).getContent();
    }

    @Override
    public List<KichCoResponse> getAll() {
        List<KichCo> kichCoList = kichCoRepository.findAll();
        return kichCoResponseMapper.listToDTO(kichCoList);
    }

    @Override
    @Transactional
    public KichCoResponse create(KichCoRequest kichCoRequest) {
        kichCoRequest.setMaKichCo(GenerateCodeRandomUtil.generateProductCode("KC", 8));
        if (kichCoRepository.getKichCoByMaKichCo(kichCoRequest.getMaKichCo()).isPresent()){
            throw new AttributeCodeDuplicateException("Mã kích cỡ " + kichCoRequest.getMaKichCo() + " đã tồn tại");
        }
        if (kichCoRepository.getKichCoByGiaTri(String.valueOf(kichCoRequest.getGiaTri())).isPresent()){
            throw new AttributeCodeDuplicateException("Giá trị kích cỡ " + kichCoRequest.getGiaTri() + " đã tồn tại");
        }
        KichCo entity = kichCoRequestMapper.toEntity(kichCoRequest);
        entity.setTrangThai(StatusEnum.ACTIVE);
        KichCo kichCo = kichCoRepository.save(entity);
        return kichCoResponseMapper.toDTO(kichCo);
    }

    @Override
    @Transactional
    public KichCoResponse update(Long id, KichCoRequest kichCoRequest) {
        KichCo kichCo = kichCoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Không tìm thấy kích cỡ với id" + id));


        kichCo.setGiaTri(kichCoRequest.getGiaTri());
        kichCo.setMoTa(kichCoRequest.getMoTa());
        kichCo.setTrangThai(StatusEnum.ACTIVE);
        kichCo.setMaKichCo(kichCoRequest.getMaKichCo());
        kichCo.setGiaTri(kichCoRequest.getGiaTri());
        kichCo.setMoTa(kichCoRequest.getMoTa());
        kichCo.setTrangThai(kichCoRequest.getTrangThai());
        KichCo kichCoUpdated = kichCoRepository.save(kichCo);
        return kichCoResponseMapper.toDTO(kichCoUpdated);
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        Optional<KichCo> optionalKichCo = kichCoRepository.findById(aLong);
        if (optionalKichCo.isPresent()){
            KichCo kichCo = optionalKichCo.get();
            kichCo.setTrangThai(StatusEnum.INACTIVE);
            kichCoRepository.save(kichCo);
        }
        else {
            throw new EntityNotFoundException("Không tìm thấy id: " + aLong);
        }
    }

    @Override
    public KichCoResponse getById(Long aLong) {
        KichCo kc = kichCoRepository.findById(aLong).orElseThrow(() ->
                new ResourceNotFoundException("Kích cỡ không tồn tại id"));
        return kichCoResponseMapper.toDTO(kc);
    }

    public Page<KichCoResponse> searchPageKichCo(Pageable pageable, KichCoSearchRequest kichCoSearchRequest){
        Page<KichCo> kichCoPage = kichCoRepository.searchPageKichCo(pageable,
                kichCoSearchRequest.getMaKichCo(),
                kichCoSearchRequest.getGiaTri()
        );
        return kichCoPage.map(kichCoResponseMapper::toDTO);
    }
}
