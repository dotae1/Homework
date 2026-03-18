package com.example.homework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "CONTENT-001", "콘텐츠를 찾을 수 없습니다."),
    CONTENT_NOT_AUTH(HttpStatus.FORBIDDEN, "CONTENT-002","게시글 작성자가 아닙니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER-001", "회원을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
