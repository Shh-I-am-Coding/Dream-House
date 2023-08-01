package com.ssafy.happy.common.aop;

import com.ssafy.happy.common.dto.ApiResponse;
import com.ssafy.happy.common.exception.BadRequestException;
import com.ssafy.happy.common.exception.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> bindException(BindException exception) {
        return ApiResponse.error(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> badRequestException(BadRequestException exception) {
        return ApiResponse.error(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> internalServerException(InternalServerErrorException exception) {
        return ApiResponse.error(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
