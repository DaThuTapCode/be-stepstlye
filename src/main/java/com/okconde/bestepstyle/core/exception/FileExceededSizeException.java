package com.okconde.bestepstyle.core.exception;

/**
 * Created by Trong Phu on 18/09/2024 12:52
 *
 * @author Trong Phu
 */
public class FileExceededSizeException extends RuntimeException{
    private Long size;
    public FileExceededSizeException(String message, Long size){
        super(message);
        this.size = size;
    }
    public Long getSize(){
        return this.size;
    }
}
