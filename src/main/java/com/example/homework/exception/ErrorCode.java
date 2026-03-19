package com.example.homework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "CONTENT-001", "콘텐츠를 찾을 수 없습니다."),
    CONTENT_NOT_AUTH(HttpStatus.FORBIDDEN, "CONTENT-002","게시글 작성자가 아닙니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER-001", "회원을 찾을 수 없습니다."),
    MEMBER_BAD_REQUEST(HttpStatus.BAD_REQUEST, "MEMBER-002", "회원의 잘못된 요청입니다."),
    MEMBER_ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER-003", "회원의 권한을 찾을 수 없습니다."),
    MEMBER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "AUTH- 001", "이미 존재하는 아이디입니다. 다른 아이디를 사용해주세요"),
    NICKNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "AUTH-001", "이미 존재하는 닉네임입니다. 다른 닉네임을 사용해주세요.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
