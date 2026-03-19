package com.example.homework.member.dto;

import lombok.Getter;

@Getter
public class JoinResponseDTO {

    private String id;
    private String password;
    private String nickname;

    private JoinResponseDTO(String nickname) {
        this.nickname = nickname;
        System.out.print(nickname + "의 회원가입을 축하합니다.");
    }

    public static JoinResponseDTO from(String nickname) {
        return new JoinResponseDTO(nickname);
    }
}
