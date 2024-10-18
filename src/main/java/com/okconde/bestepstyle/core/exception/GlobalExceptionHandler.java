package com.okconde.bestepstyle.core.exception;

import com.okconde.bestepstyle.core.objecthttp.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Trong Phu on 02/10/2024 20:52
 *
 * @author Trong Phu
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseError setData(Exception e, WebRequest request) {
        ResponseError errorResponse = new ResponseError(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getDescription(false).replace("uri", ""),
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.getReasonPhrase()
        );
        if (e instanceof MethodArgumentNotValidException) {
            String message = e.getMessage();
            int start = message.lastIndexOf("[");
            int end = message.lastIndexOf("]");
            message = message.substring(start + 1, end - 1);
            errorResponse.setMessage(message);
        }
        return errorResponse;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handlerValidationException(Exception e, WebRequest request) {
        System.out.println("[------> ĐÃ BẮT ĐƯỢC EXCEPTION MethodArgumentNotValidException]");
        return ResponseEntity.badRequest().body(setData(e, request));
    }

    @ExceptionHandler({CustomerCodeDuplicateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handlerCustomerCodeDuplicateException(Exception e, WebRequest request) {
        System.out.println("[------> ĐÃ BẮT ĐƯỢC EXCEPTION CustomerCodeDuplicateException]");
        return ResponseEntity.badRequest().body(setData(e, request));
    }

    @ExceptionHandler({EmployeeCodeDuplicateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handlerEmployeeCodeDuplicateException(Exception e, WebRequest request) {
        System.out.println("[------> ĐÃ BẮT ĐƯỢC EXCEPTION EmployeeCodeDuplicateException]");
        return ResponseEntity.badRequest().body(setData(e, request));
    }
}
