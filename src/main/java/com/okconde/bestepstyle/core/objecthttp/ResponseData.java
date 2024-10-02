package com.okconde.bestepstyle.core.objecthttp;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Trong Phu on 25/09/2024 19:09
 * Đối tượng dùng để trả về data
 * @author Trong Phu
 */
@Getter
@Setter
public class ResponseData <T>{
    private  int status;

    private  String message;

    private T data;

    //    PUT, PATCH, DELETE
    public ResponseData(int status, String message) {
        this.status = status;
        this.message = message;
    }
    //  GET, POST
    public ResponseData(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


}
