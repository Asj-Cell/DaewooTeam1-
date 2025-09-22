package com.example.backend.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AdviceRestController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse2<Void>> handleAllExceptions(Exception ex) {
         log.error("exception: {}", ex.getMessage(), ex);

        ApiResponse2<Void> errorResponse = ApiResponse2.error(0, "서버 내부 오류가 발생했습니다: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
