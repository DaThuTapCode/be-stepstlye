package com.okconde.bestepstyle.core.exception;

/**
 * Created by Trong Phu on 18/09/2024 12:40
 *
 * @author Trong Phu
 */
public class InvalidFileTypeException extends RuntimeException{

    private String fileType;

    public InvalidFileTypeException(String message, String fileType) {
        super(message);
        this.fileType = fileType;
    }

    public String getFileType(){
        return this.fileType;
    }

}
