package com.okconde.bestepstyle.core.util.enumutil;

/**
 * Created by TuanIf on 9/23/2024 21:35:14
 *
 * @author TuanIf
 */
public enum StatusHoaDon {
    TOTAL,          // All hóa đơn
    PENDING,        // Đang chờ thanh toán
    PENDINGPROCESSING, // Trạng thái chờ xác nhận
    CONFIRMED, // Đã xác nhận,
    DELIVERED, //Đã vận chuyển
    SHIPPING,       // Đang vận chuyên
    PAID,           // Đã thanh toán
    CANCELLED     // Đã hủy
}
