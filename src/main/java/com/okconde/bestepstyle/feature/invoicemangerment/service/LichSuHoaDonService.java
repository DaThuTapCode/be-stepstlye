package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.lichsuhoadon.request.LichSuHoaDonRequest;
import com.okconde.bestepstyle.core.dto.lichsuhoadon.response.LichSuHoaDonResponse;
import com.okconde.bestepstyle.core.entity.LichSuHoaDon;
import com.okconde.bestepstyle.core.service.IBaseService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by TuanIf on 9/25/2024 20:28:15
 *
 * @author TuanIf
 */
public class LichSuHoaDonService implements IBaseService<LichSuHoaDon, Long, LichSuHoaDonRequest, LichSuHoaDonResponse> {
    @Override
    public List<LichSuHoaDonResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<LichSuHoaDonResponse> getAll() {
        return null;
    }

    @Override
    public LichSuHoaDonResponse create(LichSuHoaDonRequest lichSuHoaDonRequest) {
        return null;
    }

    @Override
    public LichSuHoaDonResponse update(Long aLong, LichSuHoaDonRequest lichSuHoaDonRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public LichSuHoaDonResponse getById(Long aLong) {
        return null;
    }
}
