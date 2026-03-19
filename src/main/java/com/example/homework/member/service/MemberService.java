package com.example.homework.member.service;

import com.example.homework.exception.CustomException;
import com.example.homework.exception.ErrorCode;
import com.example.homework.member.dto.JoinRequestDTO;
import com.example.homework.member.dto.JoinResponseDTO;
import com.example.homework.member.entity.Member;
import com.example.homework.member.entity.Role;
import com.example.homework.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinResponseDTO join(JoinRequestDTO requestDTO) {

        if(memberRepository.existsByLoginId(requestDTO.getId())) {
            throw new CustomException(ErrorCode.MEMBER_ALRREADY_EXIST);
        }

        Member member = Member.create(
                requestDTO.getId(),
                bCryptPasswordEncoder.encode(requestDTO.getPassword()),
                requestDTO.getNinkname(),
                Role.USER
        );

        memberRepository.save(member);

        return JoinResponseDTO.from(member.getNickname());
    }
}
