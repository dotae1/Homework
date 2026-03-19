package com.example.homework.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class JoinRequestDTO {

    @NotNull(message = "회원의 아이디는 필수로 입력해야 합니다.")
    private String id;
    @NotNull(message = "회원의 비밀번호는 필수로 입력해야 합니다.")
    private String password;
    @NotNull(message = "닉네임은 필수로 입력해야 합니다.")
    private String ninkname;
}
