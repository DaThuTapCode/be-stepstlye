package com.okconde.bestepstyle.core.exception;

/**
 * Created by Quang Minh on 10/16/2024 21:21:57
 * exception này dùng để ném ra lỗi trùng mã nhân viên
 * @author Quang Minh
 */
public class EmployeeCodeDuplicateException extends RuntimeException{

    public EmployeeCodeDuplicateException(String message){
        super(message);
    }
}
