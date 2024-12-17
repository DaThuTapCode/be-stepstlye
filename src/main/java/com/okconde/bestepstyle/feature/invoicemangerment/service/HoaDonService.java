package com.okconde.bestepstyle.feature.invoicemangerment.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonSearchRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.entity.*;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.exception.ResourceNotFoundException;
import com.okconde.bestepstyle.core.mapper.hoadon.request.HoaDonRequestMapper;
import com.okconde.bestepstyle.core.mapper.hoadon.response.HoaDonResponseMapper;
import com.okconde.bestepstyle.core.repository.HoaDonRepository;
import com.okconde.bestepstyle.core.repository.LichSuHoaDonRepository;
import com.okconde.bestepstyle.core.repository.NhanVienRepository;
import com.okconde.bestepstyle.core.repository.SanPhamChiTietRepository;
import com.okconde.bestepstyle.core.service.IBaseService;
import com.okconde.bestepstyle.core.util.crud.GenerateCodeRandomUtil;
import com.okconde.bestepstyle.core.util.enumutil.*;
import com.okconde.bestepstyle.core.util.formater.DateFormater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
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
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    //Mapper
    private final HoaDonResponseMapper hoaDonResponseMapper;
    private final HoaDonRequestMapper hoaDonRequestMapper;
    private final LichSuHoaDonRepository lichSuHoaDonRepository;
    private final NhanVienRepository nhanVienRepository;

    public HoaDonService(HoaDonResponseMapper hoaDonResponseMapper, HoaDonRepository hoaDonRepository, SanPhamChiTietRepository sanPhamChiTietRepository, HoaDonRequestMapper hoaDonRequestMapper, LichSuHoaDonRepository lichSuHoaDonRepository, NhanVienRepository nhanVienRepository) {
        this.hoaDonResponseMapper = hoaDonResponseMapper;
        this.hoaDonRepository = hoaDonRepository;
        this.sanPhamChiTietRepository = sanPhamChiTietRepository;
        this.hoaDonRequestMapper = hoaDonRequestMapper;
        this.lichSuHoaDonRepository = lichSuHoaDonRepository;
        this.nhanVienRepository = nhanVienRepository;
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

    /**Hàm xuất hóa đơn dưới dạng pdf*/
    public byte[] generateInvoice(Long idHoaDon) throws DocumentException, IOException {
        // Tạo tài liệu PDF
        HoaDon hoaDon = hoaDonRepository.findByIdHoaDonAndTrangThai(idHoaDon, StatusHoaDon.PAID)
                .orElseThrow(() -> new BusinessException("Hóa đơn không tồn tại hoặc chưa được thanh toán"));
        Document doc = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter.getInstance(doc, baos);
        doc.open();

        // Lấy ngày và giờ hiện tại
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String nowFormatted = formatter.format(now);

        // Thêm logo vào hóa đơn
        URL url = getClass().getResource("/LogoStepStyle.png");
        Image logo = Image.getInstance(url);
        logo.scaleAbsolute(60f, 60f);
        logo.setAlignment(Element.ALIGN_LEFT);
        doc.add(logo);

        // Định nghĩa font tiếng Việt
        BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font titleFont = new Font(baseFont, 24, Font.BOLD, BaseColor.BLACK);
        Font infoFont = new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
        Font deliveryInfoFont = new Font(baseFont, 12, Font.ITALIC, BaseColor.DARK_GRAY);
        Font footerFont = new Font(baseFont, 12, Font.ITALIC, BaseColor.DARK_GRAY);

        // Tiêu đề hóa đơn
        Paragraph title = new Paragraph("HÓA ĐƠN BÁN LẺ", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        doc.add(title);
        doc.add(new Paragraph("\n"));

        // Thông tin hóa đơn
        doc.add(new Paragraph("Mã hóa đơn: " + hoaDon.getMaHoaDon(), infoFont));
        doc.add(new Paragraph("Tên khách hàng: " + hoaDon.getKhachHang().getTenKhachHang(), infoFont));
        doc.add(new Paragraph("Ngày tạo hóa đơn: " + hoaDon.getNgayTaoDon(), infoFont));
        doc.add(new Paragraph("Ngày xuất hóa đơn: " + nowFormatted, infoFont));
        doc.add(new Paragraph("\n"));

        // Kiểm tra trạng thái và thêm thông tin giao hàng nếu là OnlineSales
        if (hoaDon.getLoaiHoaDon() == LoaiHoaDon.ONLINESALES) {
            doc.add(new Paragraph("Thông tin giao hàng:", deliveryInfoFont));
            doc.add(new Paragraph("Địa chỉ giao hàng: " + hoaDon.getDiaChiGiaoHang(), infoFont));
            doc.add(new Paragraph("Số điện thoại: " + hoaDon.getSoDienThoaiKhachHang(), infoFont));
            if (hoaDon.getNgayNhanHang() != null) {
                doc.add(new Paragraph("Ngày nhận hàng dự kiến: " + hoaDon.getNgayNhanHang(), infoFont));
            }
            doc.add(new Paragraph("\n"));
        }

        // Tạo bảng sản phẩm (các phần còn lại không thay đổi)
        PdfPTable tbl = new PdfPTable(8);
        tbl.setWidthPercentage(100);
        tbl.setSpacingBefore(10f);

        // Tiêu đề bảng
        PdfPCell headerCell;
        String[] headers = {"Tên sản phẩm", "Size", "Màu sắc", "Chất liệu", "Thương hiệu", "Đơn giá", "Số lượng", "Thành tiền"};
        for (String header : headers) {
            headerCell = new PdfPCell(new Phrase(header, infoFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setPadding(5);
            tbl.addCell(headerCell);
        }

        // Thêm dữ liệu sản phẩm vào bảng
        for (HoaDonChiTiet chiTiet : hoaDon.getHoaDonChiTiet()) {
            tbl.addCell(new PdfPCell(new Phrase(chiTiet.getSanPhamChiTiet().getSanPham().getTenSanPham(), infoFont)));
            tbl.addCell(new PdfPCell(new Phrase(chiTiet.getSanPhamChiTiet().getKichCo().getGiaTri() + "", infoFont)));
            tbl.addCell(new PdfPCell(new Phrase(chiTiet.getSanPhamChiTiet().getMauSac().getTenMau(), infoFont)));
            tbl.addCell(new PdfPCell(new Phrase(chiTiet.getSanPhamChiTiet().getSanPham().getChatLieu().getTenChatLieu(), infoFont)));
            tbl.addCell(new PdfPCell(new Phrase(chiTiet.getSanPhamChiTiet().getSanPham().getThuongHieu().getTenThuongHieu(), infoFont)));
            tbl.addCell(new PdfPCell(new Phrase(formatCurrency(chiTiet.getDonGia()), infoFont)));
            tbl.addCell(new PdfPCell(new Phrase(String.valueOf(chiTiet.getSoLuong()), infoFont)));
            tbl.addCell(new PdfPCell(new Phrase(formatCurrency(chiTiet.getTongTien()), infoFont)));
        }

        // Tổng tiền
        PdfPCell totalCell = new PdfPCell(new Phrase("Tổng cộng: " + formatCurrency(hoaDon.getTongTien()), infoFont));
        totalCell.setColspan(8);
        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalCell.setPadding(10);
        tbl.addCell(totalCell);

        doc.add(tbl);

        // Lời cảm ơn
        Paragraph thankYou = new Paragraph("CHÚC QUÝ KHÁCH MUA SẮM VUI VẺ!", footerFont);
        thankYou.setAlignment(Element.ALIGN_CENTER);
        doc.add(thankYou);

        doc.close();

        return baos.toByteArray();
    }

    private String formatCurrency(BigDecimal amount) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(amount) + "đ";
    }



    /** HỦy hóa đơn bán online*/
        @Transactional
    public boolean cancelInvoiceOnline(Long idHoaDon) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).orElseThrow(() -> new BusinessException("Không tìm thấy hóa đơn cần hủy!"));

        if(hoaDon.getTrangThai() == StatusHoaDon.PAID) {
            throw new BusinessException("Hóa đơn đã thanh toán không thể hủy");
        }
        hoaDon.setTrangThai(StatusHoaDon.CANCELLED);
        return true;
    }


    /**
     * Thay đổi trạng thái hóa đơn
     */
    @Transactional
    public boolean changeStatusInvoice(Long idHoaDon, String maNV, StatusHoaDon trangThaiMoi) {
        NhanVien nv  = nhanVienRepository.timNVTheoMaNVVaTrangThai(maNV, StatusEnum.ACTIVE).orElseThrow(
                () -> new BusinessException("Nhân viên không hợp lệ")
        );

        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon)
                .orElseThrow(() -> new BusinessException("Không tìm thấy hóa đơn cần đổi trạng thái"));

        if(hoaDon.getTrangThai() == StatusHoaDon.CANCELLED){
            throw new BusinessException("Hóa đơn này đã bị hủy!");
        }

        if(hoaDon.getTrangThai() == StatusHoaDon.PENDINGPROCESSING) {
            for (HoaDonChiTiet hdct : hoaDon.getHoaDonChiTiet()) {
                SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.getSPCTByIdSPCTAndTrangThai(hdct.getSanPhamChiTiet().getIdSpct(), StatusSPCT.ACTIVE).orElseThrow(
                        () -> new BusinessException("Không tìm thấy sản phẩm"));
                if (sanPhamChiTiet.getSoLuong() < hdct.getSoLuong()) {
                    throw new BusinessException("Số lượng sản phẩm " + sanPhamChiTiet.getMaSpct() + "trong kho không đủ!");
                }
                sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - hdct.getSoLuong());
            }
        }

        // Định nghĩa thứ tự trạng thái
        Map<StatusHoaDon, Integer> trangThaiMap = Map.of(
                StatusHoaDon.PENDINGPROCESSING, 1,
                StatusHoaDon.CONFIRMED, 2,
                StatusHoaDon.SHIPPING, 3,
                StatusHoaDon.DELIVERED, 4,
                StatusHoaDon.PAID, 5
        );

        // Lấy trạng thái hiện tại
        StatusHoaDon trangThaiHienTai = hoaDon.getTrangThai();

        // Kiểm tra nếu trạng thái mới nhỏ hơn hoặc bằng trạng thái hiện tại
        if (trangThaiMap.get(trangThaiMoi) <= trangThaiMap.get(trangThaiHienTai)) {
            throw new BusinessException("Trạng thái hóa đơn sai tiến trình hiện tại");
        }

        // Cập nhật trạng thái
        hoaDon.setTrangThai(trangThaiMoi);
//        if(trangThaiMoi === StatusHoaDon.CONFIRMED)
       HoaDon hoaDonSaved = hoaDonRepository.save(hoaDon);
        LichSuHoaDon lichSuHoaDon = LichSuHoaDon.builder()
                .hoaDon(hoaDonSaved)
                .maLichSuHoaDon(GenerateCodeRandomUtil.generateProductCode("LSHD", 6))
                .hanhDong(trangThaiMoi.toString())
                .ngayTao(LocalDateTime.now())
                .nguoiThucHien( maNV + "-" + nv.getHoTen())
                .trangThai(StatusEnum.ACTIVE)
                .build();
        lichSuHoaDonRepository.save(lichSuHoaDon);
        return true;
    }

}
