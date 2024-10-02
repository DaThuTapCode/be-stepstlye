package com.okconde.bestepstyle.core.objecthttp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Trong Phu on 25/09/2024 19:10
 * Đối tượng trả về dữ liệu lỗi
 * @author Trong Phu
 */
@Getter
@Setter
public class ResponseError extends ResponseData{

    private String path;

    private LocalDateTime timestamp;

    private String error;

    public ResponseError(int status, String message, String path, LocalDateTime timestamp, String error) {
        super(status, message);
        this.path = path;
        this.timestamp = timestamp;
        this.error = error;
    }
}

