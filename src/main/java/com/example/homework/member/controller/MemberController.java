package com.example.homework.member.controller;

import com.example.homework.exception.SuccessCode;
import com.example.homework.exception.SuccessResponseEntity;
import com.example.homework.member.dto.JoinRequestDTO;
import com.example.homework.member.dto.JoinResponseDTO;
import com.example.homework.member.dto.LoginRequestDTO;
import com.example.homework.member.dto.UpdateRoleRequestDTO;
import com.example.homework.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @PostMapping("/join")
    public ResponseEntity<SuccessResponseEntity<JoinResponseDTO>> join (
            @RequestBody @Valid JoinRequestDTO requestDTO
            ) {

        JoinResponseDTO responseDTO = memberService.join(requestDTO);

        return SuccessResponseEntity.toResponseEntity(SuccessCode.JOIN_SUCCESS, responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (
            @RequestBody LoginRequestDTO requestDTO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        securityContextRepository.saveContext(context, request, response);

        return ResponseEntity.ok(SuccessCode.LOGIN_SUCCESS);
    }

    @Operation (
            summary = "로그아웃", description = "세션 제거 + 로그아웃"
    ) @PostMapping("/logout")
    public void logout() {
        // Swagger테스트용
    }

    //TEST용
    @PostMapping("/change")
    public ResponseEntity<?> changeRole (
            @RequestBody UpdateRoleRequestDTO requestDTO,
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        memberService.updateRole(userDetails.getUsername(), requestDTO.getRole());
        return ResponseEntity.ok(SuccessCode.LOGIN_SUCCESS);
    }
}
