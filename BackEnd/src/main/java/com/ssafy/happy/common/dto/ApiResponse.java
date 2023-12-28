package com.ssafy.happy.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;


    public static <T> ResponseEntity<ApiResponse<T>> successWithNoContent() {
        return new ResponseEntity<>(
                ApiResponse.<T>builder()
                        .success(true)
                        .data(null)
                        .message(null)
                        .build(), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> successWithData(T data) {
        return new ResponseEntity<>(
                ApiResponse.<T>builder()
                        .success(true)
                        .data(data)
                        .message(null)
                        .build(), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(
                ApiResponse.<T>builder()
                        .success(false)
                        .data(null)
                        .message(message)
                        .build(), httpStatus);
    }
}
