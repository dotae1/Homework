package com.example.homework.member.controller;

import com.example.homework.exception.SuccessCode;
import com.example.homework.exception.SuccessResponseEntity;
import com.example.homework.member.dto.JoinRequestDTO;
import com.example.homework.member.dto.JoinResponseDTO;
import com.example.homework.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<SuccessResponseEntity<JoinResponseDTO>> join (
            @RequestBody @Valid JoinRequestDTO requestDTO
            ) {

        JoinResponseDTO responseDTO = memberService.join(requestDTO);

        return SuccessResponseEntity.toResponseEntity(SuccessCode.JOIN_SUCCESS, responseDTO);
    }
}
