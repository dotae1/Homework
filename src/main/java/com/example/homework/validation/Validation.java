package com.example.homework.validation;

import com.example.homework.contents.entity.Content;
import com.example.homework.contents.repository.ContentRepository;
import com.example.homework.exception.CustomException;
import com.example.homework.exception.ErrorCode;
import com.example.homework.member.entity.Member;
import com.example.homework.member.entity.Role;
import com.example.homework.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Validation {

    private final MemberRepository memberRepository;
    private final ContentRepository contentRepository;

    /**
     * 회원이 존재하는지 여부 검증로직
     */
    public Member memberValidation(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return member;
    }

    /**
     * 콘텐츠 작성자와 memberId가 맞는지 검증 로직
     */
    public Content contentValidation(Long contentId, Long memberId) {
        Content content = contentRepository.findByIdAndMember_MemberId(contentId, memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.CONTENT_NOT_AUTH));

        return content;
    }

    /**
     * 콘텐츠가 존재하는지 여부 검증로직
     */
    public Content contentFoundValidation(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new CustomException(ErrorCode.CONTENT_NOT_FOUND));
        return content;
    }

    /**
     * Role이 존재하는지 검증 여부
     */
    public Role memberRoleValidation(Long memberId) {
        Role role = memberRepository.findRoleById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_ROLE_NOT_FOUND));
        return role;
    }

}
