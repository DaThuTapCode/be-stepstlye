package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonSearchRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.hoadon.request.HoaDonRequestMapper;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonResponseMapper;
import com.okconde.bestepstyle.core.repository.HoaDonRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import com.okconde.bestepstyle.core.util.formater.DateFormater;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by TuanIf on 9/25/2024 20:27:52
 *
 * @author TuanIf
 */

@Service(value = "HoaDonService")

public class HoaDonService implements IBaseService<HoaDon, Long, HoaDonRequest, HoaDonResponse> {
    //Repo
    private final HoaDonRepository hoaDonRepository;

    //Mapper
    private final HoaDonResponseMapper hoaDonResponseMapper;
    private final HoaDonRequestMapper hoaDonRequestMapper;

    public HoaDonService(HoaDonResponseMapper hoaDonResponseMapper, HoaDonRepository hoaDonRepository, HoaDonRequestMapper hoaDonRequestMapper) {
        this.hoaDonResponseMapper = hoaDonResponseMapper;
        this.hoaDonRepository = hoaDonRepository;
        this.hoaDonRequestMapper = hoaDonRequestMapper;
    }


    @Override
    public List<HoaDonResponse> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<HoaDonResponse> getAll() {
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        return hoaDonResponseMapper.listToDTO(hoaDonList);
    }

    @Override
    @Transactional
    public HoaDonResponse create(HoaDonRequest hoaDonRequest) {
        HoaDon hoaDonNew = hoaDonRequestMapper.toEntity(hoaDonRequest);
        hoaDonNew.setNgayTaoDon(LocalDateTime.now());
        hoaDonNew.setPhiVanChuyen(hoaDonNew.getPhiVanChuyen());
        hoaDonNew.setTongTien(hoaDonNew.getTongTien());
        hoaDonNew.setTongTienSauGiam(hoaDonNew.getTongTienSauGiam());
        hoaDonNew.setNgayChinhSua(LocalDateTime.now());
        hoaDonNew.setNgayXacNhan(LocalDateTime.now());
        hoaDonNew.setNgayNhanHang(LocalDateTime.now());
        hoaDonNew.setLoaiHoaDon(hoaDonNew.getLoaiHoaDon());
        hoaDonNew.setTenKhachHang(hoaDonNew.getTenKhachHang());
        hoaDonNew.setDiaChiGiaoHang(hoaDonNew.getDiaChiGiaoHang());
        hoaDonNew.setSoDienThoaiKhachHang(hoaDonNew.getSoDienThoaiKhachHang());
        hoaDonNew.setGhiChu(hoaDonNew.getGhiChu());
        hoaDonNew.setTrangThai(StatusHoaDon.PAID);

        HoaDon hoaDonSaved = hoaDonRepository.save(hoaDonNew);
        return hoaDonResponseMapper.toDTO(hoaDonSaved);
    }

    @Override
    @Transactional
    public HoaDonResponse update(Long id, HoaDonRequest hoaDonRequest) {
        HoaDon hoaDonExisting = hoaDonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy với id: " + id));

        hoaDonExisting.setNgayTaoDon(LocalDateTime.now());
        hoaDonExisting.setPhiVanChuyen(hoaDonRequest.getPhiVanChuyen());
        hoaDonExisting.setTongTien(hoaDonRequest.getTongTien());
        hoaDonExisting.setTongTienSauGiam(hoaDonRequest.getTongTienSauGiam());
        hoaDonExisting.setNgayChinhSua(LocalDateTime.now());
        hoaDonExisting.setNgayXacNhan(LocalDateTime.now());
        hoaDonExisting.setNgayNhanHang(LocalDateTime.now());
        hoaDonExisting.setLoaiHoaDon(hoaDonRequest.getLoaiHoaDon());
        hoaDonExisting.setTenKhachHang(hoaDonRequest.getTenKhachHang());
        hoaDonExisting.setDiaChiGiaoHang(hoaDonRequest.getDiaChiGiaoHang());
        hoaDonExisting.setSoDienThoaiKhachHang(hoaDonRequest.getSoDienThoaiKhachHang());
        hoaDonExisting.setGhiChu(hoaDonRequest.getGhiChu());
        hoaDonExisting.setTrangThai(hoaDonRequest.getTrangThai());

        HoaDon hoaDonUpdated = hoaDonRepository.save(hoaDonExisting);
        return hoaDonResponseMapper.toDTO(hoaDonUpdated);
    }

    @Override
    public void delete(Long id) {

        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hóa đơn không tồn tại"));
                hoaDon.setTrangThai(StatusHoaDon.PAID);
                hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDonResponse getById(Long id) {
        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id: " + id));
        return hoaDonResponseMapper.toDTO(hoaDon);
    }

    public Page<HoaDonResponse> searchPageHoaDon(Pageable pageable, HoaDonSearchRequest hoaDonSearchRequest){
        Page<HoaDon> hoaDonPage = hoaDonRepository.searchPageHoaDon(pageable,
                hoaDonSearchRequest.getMaHoaDon(),
                hoaDonSearchRequest.getNgayTaoStart(),
                DateFormater.setEndDate(hoaDonSearchRequest.getNgayTaoEnd()),
                hoaDonSearchRequest.getIdKhachHang(),
                hoaDonSearchRequest.getIdNhanVien(),
                hoaDonSearchRequest.getIdThanhToan(),
                hoaDonSearchRequest.getIdPhieuGiamGia()
                );
        return hoaDonPage.map(hoaDonResponseMapper::toDTO);
    }
}
