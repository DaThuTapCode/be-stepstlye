package com.okconde.bestepstyle.core.exception;

/**
 * Created by Quang Minh on 10/16/2024 21:05:26
 * exception này dùng để ném ra lỗi trùng mã khách hàng
 * @author Quang Minh
 */
public class CustomerCodeDuplicateException extends RuntimeException{

    public CustomerCodeDuplicateException(String message){
        super(message);
    }
}
