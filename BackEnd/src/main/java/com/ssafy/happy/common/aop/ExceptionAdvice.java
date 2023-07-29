package com.ssafy.happy.common.aop;

import com.ssafy.happy.board.exception.NotExistBoardException;
import com.ssafy.happy.common.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<?>> notExistBoardException(NotExistBoardException exception) {
        return ApiResponse.error(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
