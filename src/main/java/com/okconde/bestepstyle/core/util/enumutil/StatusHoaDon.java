package com.okconde.bestepstyle.core.util.enumutil;

/**
 * Created by TuanIf on 9/23/2024 21:35:14
 *
 * @author TuanIf
 */
public enum StatusHoaDon {
    TOTAL,          // All hóa đơn
    PENDING,        // Đang chờ thanh toán
    PENDINGPROCESSING, // Trạng thái chờ xử lý
    SHIPPING,       // Đang vận chuyên
    PAID,           // Đã thanh toán
    CANCELLED,      // Đã hủy
    REFUNDED       // Đã hoàn tiền     // Quá hạn
}
