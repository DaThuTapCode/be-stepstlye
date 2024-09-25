package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.repository.DanhMucRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by TuanIf on 9/25/2024 20:27:52
 *
 * @author TuanIf
 */
public class HoaDonService implements IBaseService <HoaDon, Long, HoaDonRequest, HoaDonResponse> {

    private final DanhMucRepository danhMucRepository;

    public HoaDonService(DanhMucRepository danhMucRepository) {
        this.danhMucRepository = danhMucRepository;
    }


    @Override
    public List<HoaDonResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<HoaDonResponse> getAll() {
        return null;
    }

    @Override
    public HoaDonResponse create(HoaDonRequest hoaDonRequest) {
        return null;
    }

    @Override
    public HoaDonResponse update(Long aLong, HoaDonRequest hoaDonRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public HoaDonResponse getById(Long aLong) {
        return null;
    }
}
