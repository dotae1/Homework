package com.example.homework.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class SuccessResponseEntity<T> {

    private int status;
    private String name;
    private String code;
    private String message;
    private T data;

    public static <T> ResponseEntity<SuccessResponseEntity<T>> toResponseEntity(SuccessCode s, T data) {
        return ResponseEntity
                .status(s.getStatus())
                .body(SuccessResponseEntity.<T>builder()
                        .status(s.getStatus().value())
                        .name(s.name())
                        .code(s.getCode())
                        .message(s.getMessage())
                        .data(data)
                        .build());
    }

    public static ResponseEntity<SuccessResponseEntity<Void>> toResponseEntity(SuccessCode s) {
        return toResponseEntity(s, null);
    }
}