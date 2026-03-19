package com.example.homework.member.service;

import com.example.homework.member.dto.JoinRequestDTO;
import com.example.homework.member.dto.JoinResponseDTO;
import com.example.homework.member.entity.Member;
import com.example.homework.member.entity.Role;
import com.example.homework.member.repository.MemberRepository;
import com.example.homework.validation.Validation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Validation validation;

    public JoinResponseDTO join(JoinRequestDTO requestDTO){

        validation.existLoginIdValidation(requestDTO.getId());
        validation.existNicknameValidation(requestDTO.getNickname());

        Member member = Member.create(
                requestDTO.getId(),
                bCryptPasswordEncoder.encode(requestDTO.getPassword()),
                requestDTO.getNickname(),
                Role.USER
        );

        memberRepository.save(member);

        return JoinResponseDTO.from(member.getNickname());
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        Member member = validation.memberValidation(loginId);

        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getLoginId())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }

    // TEST용
//    @Transactional
//    public void updateRole(String loginId, Role newRole) {
//        Member member = validation.memberValidation(loginId);
//
//        member.changeRole(newRole);
//    }
}
