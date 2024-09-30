package com.okconde.bestepstyle.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Trong Phu on 26/09/2024 21:16
 *
 * @author Trong Phu
 */
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    public ResourceNotFoundException (String message){
        super(message);
    }
    public ResourceNotFoundException (String message, String resourceName){
        super(message);
        this.resourceName = resourceName;
    }
}
