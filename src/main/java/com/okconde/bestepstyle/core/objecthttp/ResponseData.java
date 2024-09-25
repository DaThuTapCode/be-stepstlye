package com.okconde.bestepstyle.core.objecthttp;

/**
 * Created by Trong Phu on 25/09/2024 19:09
 * Đối tượng dùng để trả về data
 * @author Trong Phu
 */
public class ResponseData <T>{
    private final int status;

    private final String message;

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

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
