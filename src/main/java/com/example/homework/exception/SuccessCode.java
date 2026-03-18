package com.example.homework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    CONTENT_CREATED(HttpStatus.CREATED, "CONTENT-001", "콘텐츠 생성에 성공했습니다."),
    CONTENT_UPDATED(HttpStatus.OK, "CONTENT-002", "콘텐츠 수정에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;


}
