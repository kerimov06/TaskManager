package com.turan.handler;

import com.turan.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<String> handlerException(BaseException exception){

         return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
