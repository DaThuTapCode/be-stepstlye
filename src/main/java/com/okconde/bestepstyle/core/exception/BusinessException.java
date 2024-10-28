package com.okconde.bestepstyle.core.exception;

/**
 * Created by Trong Phu on 27/10/2024 23:11
 * Ném ra lỗi nghiệp vụ
 * @author Trong Phu
 */
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
