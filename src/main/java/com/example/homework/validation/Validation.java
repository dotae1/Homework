package com.example.homework.validation;

import com.example.homework.contents.entity.Content;
import com.example.homework.contents.repository.ContentRepository;
import com.example.homework.exception.CustomException;
import com.example.homework.exception.ErrorCode;
import com.example.homework.member.entity.Member;
import com.example.homework.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Validation {

    private final MemberRepository memberRepository;
    private final ContentRepository contentRepository;

    public Member memberValidation(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return member;
    }

    public Content contentValidation(Long contentId, Long memberId) {
        Content content = contentRepository.findByIdAndMemberId(contentId, memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.CONTENT_NOT_AUTH));

        return content;
    }
}
