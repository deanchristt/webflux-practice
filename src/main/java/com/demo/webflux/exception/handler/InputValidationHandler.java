package com.demo.webflux.exception.handler;

import com.demo.webflux.dto.InputFailedValidation;
import com.demo.webflux.exception.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidation> handleException(InputValidationException ex){
        return ResponseEntity.badRequest().body(InputFailedValidation.builder()
                .input(ex.getInput())
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .build());
    }
}
