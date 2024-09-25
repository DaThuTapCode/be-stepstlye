package com.okconde.bestepstyle.core.objecthttp;

/**
 * Created by Trong Phu on 25/09/2024 19:10
 * Đối tượng trả về dữ liệu lỗi
 * @author Trong Phu
 */
public class ResponseError extends ResponseData{
    public ResponseError(int status, String message) {
        super(status, message);
    }
}

