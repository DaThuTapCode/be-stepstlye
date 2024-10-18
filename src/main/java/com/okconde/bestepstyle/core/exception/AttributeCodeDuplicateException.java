package com.okconde.bestepstyle.core.exception;

/**
 * Created at 18/10/2024 by Ngo Tu
 * Dùng để ném ra lỗi trùng mã thuộc tính
 * @author: Ngo Tu
 */
public class AttributeCodeDuplicateException extends RuntimeException{
    public AttributeCodeDuplicateException(String message){
        super(message);
    }
}
