package com.springboot.Locationmanagementapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BusinessException.class)
public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException exception){

    return new ResponseEntity<>(exception.getErrorList(), HttpStatus.BAD_REQUEST);

}

}
