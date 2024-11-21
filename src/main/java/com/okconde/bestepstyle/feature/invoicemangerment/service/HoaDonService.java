package com.okconde.bestepstyle.feature.invoicemangerment.service;

//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonSearchRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.entity.HoaDonChiTiet;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.hoadon.request.HoaDonRequestMapper;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonResponseMapper;
import com.okconde.bestepstyle.core.repository.HoaDonRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.LoaiHoaDon;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import com.okconde.bestepstyle.core.util.formater.DateFormater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        hoaDonRequest.setMaHoaDon(GenerateCodeRandomUtil.generateProductCode("HD",8));
        hoaDonRequest.setTrangThai(StatusHoaDon.PAID);

        return hoaDonResponseMapper.toDTO(hoaDonRepository.save(hoaDonRequestMapper.toEntity(hoaDonRequest)));
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
                hoaDonSearchRequest.getTenKhachHang(),
                hoaDonSearchRequest.getSoDienThoai(),
                hoaDonSearchRequest.getTrangThai(),
                hoaDonSearchRequest.getLoaiHoaDon()
                );
        return hoaDonPage.map(hoaDonResponseMapper::toDTO);
    }

    public Map<String, Integer> getHoaDonByStatus() {
            int pendingCount = hoaDonRepository.countByStatus(StatusHoaDon.PENDING);
            int pendingProcessingCount = hoaDonRepository.countByStatus(StatusHoaDon.PENDINGPROCESSING);
            int shippingCount = hoaDonRepository.countByStatus(StatusHoaDon.SHIPPING);
            int paidCount = hoaDonRepository.countByStatus(StatusHoaDon.PAID);
            int cancelledCount = hoaDonRepository.countByStatus(StatusHoaDon.CANCELLED);

            //Tất cả hóa đơn
            int totalCount = pendingCount + pendingProcessingCount + shippingCount + paidCount + cancelledCount;

            Map<String, Integer> counts = new HashMap<>();
            counts.put("TOTAL", totalCount);
            counts.put("PENDING", pendingCount);
            counts.put("PENDINGPROCESSING", pendingProcessingCount);
            counts.put("SHIPPING", shippingCount);
            counts.put("PAID", paidCount);
            counts.put("CANCELLED", cancelledCount);

            return counts;
    }

    public List<HoaDonResponse> getHoaDonByLoaiHoaDon(LoaiHoaDon loaiHoaDon) {
        List<HoaDon> hoaDonList = hoaDonRepository.getHoaDon(loaiHoaDon);
        return hoaDonResponseMapper.listToDTO(hoaDonList);
    }

//    /**Hàm xuất hóa đơn dưới dạng pdf*/
//    public byte[] generateInvoice(Long idHoaDon) throws DocumentException, DocumentException, IOException {
//        // Tạo tài liệu PDF
//        HoaDon hoaDon = hoaDonRepository.findByIdHoaDonAndTrangThai(idHoaDon, StatusHoaDon.PAID)
//                .orElseThrow(() -> new BusinessException("Hóa đơn không tồn tại hoặc chưa được thanh toán"));
//        Document doc = new Document();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        PdfWriter.getInstance(doc, baos);
//        doc.open();
//
//        // Lấy ngày và giờ hiện tại
//        Date now = new Date();
//        SimpleDateFormat fomater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String nowS = fomater.format(now);
//
//        // Thêm logo vào hóa đơn
////        URL url = getClass().getResource("/img/nike.png");
////        Image logo = Image.getInstance(url);
////        logo.scaleAbsolute(60f, 60f);
////        logo.setAlignment(Element.ALIGN_LEFT);
////        doc.add(logo);
//        // Định nghĩa font tiếng Việt
//        BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//        Font titleFont = new Font(baseFont, 32, Font.BOLD, BaseColor.BLACK);
//        Font infoFont = new Font(baseFont, 12, Font.NORMAL, BaseColor.DARK_GRAY);
//        Font tableHeaderFont = new Font(baseFont, 12, Font.BOLD, BaseColor.LIGHT_GRAY);
//        Font totalFont = new Font(baseFont, 12, Font.BOLD, BaseColor.BLACK);
//        Font footerFont = new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
//
//        // Thêm tiêu đề hóa đơn
////        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD, BaseColor.BLACK);
//        Paragraph title = new Paragraph("HOA DON BAN LE ", titleFont);
//        title.setAlignment(Paragraph.ALIGN_CENTER);
//        doc.add(title);
//        doc.add(new Paragraph("\n"));
//
//        // Thêm thông tin hóa đơn
////        Font infoFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.DARK_GRAY);
//        doc.add(new Paragraph("Ma hoa don: " + hoaDon.getMaHoaDon(), infoFont));
//        doc.add(new Paragraph("Ten khach hang: " + hoaDon.getTenKhachHang(), infoFont));
//        doc.add(new Paragraph("Ngay tao hoa don: " + hoaDon.getNgayTaoDon(), infoFont));
//        doc.add(new Paragraph("Ngay xuat hoa don: " + nowS, infoFont));
//
//        // Tạo bảng sản phẩm
//        PdfPTable tbl = new PdfPTable(8);
//        tbl.setWidthPercentage(100);
//        tbl.setSpacingBefore(10f);
//        tbl.setSpacingAfter(10f);
//
//        // Tiêu đề bảng
////        Font tableHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.LIGHT_GRAY);
//        tbl.addCell(new PdfPCell(new Phrase("Ten san pham", tableHeaderFont)));
//        tbl.addCell(new PdfPCell(new Phrase("Size", tableHeaderFont)));
//        tbl.addCell(new PdfPCell(new Phrase("Mau sac", tableHeaderFont)));
//        tbl.addCell(new PdfPCell(new Phrase("Chat lieu", tableHeaderFont)));
//        tbl.addCell(new PdfPCell(new Phrase("Hang", tableHeaderFont)));
//        tbl.addCell(new PdfPCell(new Phrase("Don gia", tableHeaderFont)));
//        tbl.addCell(new PdfPCell(new Phrase("So luong", tableHeaderFont)));
//        tbl.addCell(new PdfPCell(new Phrase("Thanh tien", tableHeaderFont)));
//
//        // Thêm dữ liệu sản phẩm vào bảng
//        for (HoaDonChiTiet hoaDonChiTiet : hoaDon.getHoaDonChiTiet()) { // Ví dụ, thay 5 bằng số dòng thực tế của bạn
//            tbl.addCell(hoaDonChiTiet.getSanPhamChiTiet().getSanPham().getTenSanPham());
//            tbl.addCell(hoaDonChiTiet.getSanPhamChiTiet().getKichCo().getGiaTri() + "");
//            tbl.addCell(hoaDonChiTiet.getSanPhamChiTiet().getMauSac().getTenMau());
//            tbl.addCell(hoaDonChiTiet.getSanPhamChiTiet().getChatLieu().getTenChatLieu());
//            tbl.addCell(hoaDonChiTiet.getSanPhamChiTiet().getSanPham().getThuongHieu().getTenThuongHieu());
//            tbl.addCell(hoaDonChiTiet.getDonGia() + "đ");
//            tbl.addCell(hoaDonChiTiet.getSoLuong() + "đ");
//            tbl.addCell(hoaDonChiTiet.getTongTien() + "đ");
//        }
//
//        // Tổng tiền
////        Font totalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
//        PdfPCell totalCell = new PdfPCell(new Phrase(hoaDon.getTongTien() + "đ", totalFont));
//        totalCell.setColspan(8);
//        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        tbl.addCell(totalCell);
//
//        doc.add(tbl);
//
//        // Thêm thông tin thanh toán
////        Font footerFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
//        doc.add(new Paragraph("\n"));
//        doc.add(new Paragraph("Hạn trả hàng 2 ngày sau khi thanh toán hóa đơn", footerFont));
//        doc.add(new Paragraph("CHÚC QUÝ KHÁCH MUA SẮM VUI VẺ!!!", footerFont));
//
//        doc.close();
//
//        // Trả về PDF dưới dạng byte[]
//        return baos.toByteArray();
//    }

}
