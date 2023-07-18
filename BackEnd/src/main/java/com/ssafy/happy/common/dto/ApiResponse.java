package com.ssafy.happy.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;

    public static <T> ApiResponse<T> ok(boolean success) {
        return ApiResponse.<T>builder()
                .success(success)
                .message("success")
                .build();
    }
    public static <T> ApiResponse<T> of(boolean success, T data, String message) {
        return ApiResponse.<T>builder()
                .success(success)
                .data(data)
                .message(message)
                .build();
    }
}
