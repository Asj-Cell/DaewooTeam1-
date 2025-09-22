package com.example.backend.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse2<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse2<T> success(T data) {
        return new ApiResponse2<>(200, "Success", data);
    }

    public static <T> ApiResponse2<T> error(int code, String message) {
        return new ApiResponse2<>(code, message, null);
    }
}