package com.example.homework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    CONTENT_CREATED(HttpStatus.CREATED, "CONTENT-001", "콘텐츠 생성에 성공했습니다."),
    CONTENT_UPDATED(HttpStatus.OK, "CONTENT-002", "콘텐츠 수정에 성공했습니다."),
    CONTENT_LIST_SUCCESS_SEARCH(HttpStatus.OK, "CONTENT-003", "콘텐츠 목록 조회에 성공했습니다."),
    CONTENT_SUCCESS_SEARCH(HttpStatus.OK, "CONTENT-004", "콘텐츠 상세 조회를 성공했습니다."),

    JOIN_SUCCESS(HttpStatus.OK, "AUTH-001", "회원가입이 성공적으로 완료되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "AUTH-002", "로그인이 성공적으로 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;


}
