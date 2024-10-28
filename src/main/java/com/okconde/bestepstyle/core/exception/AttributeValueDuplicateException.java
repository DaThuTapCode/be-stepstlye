package com.okconde.bestepstyle.core.exception;

/**
 * Created at 25/10/2024 by Ngo Tu
 * Dùng để ném ra lỗi trùng tên thuộc tính
 * @author: Ngo Tu
 */
public class AttributeValueDuplicateException extends RuntimeException{
    public AttributeValueDuplicateException(String message){
        super(message);
    }
}
