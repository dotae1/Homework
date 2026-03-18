package com.example.homework.contents.service;

import com.example.homework.exception.CustomException;
import com.example.homework.exception.ErrorCode;
import com.example.homework.contents.dto.CreateContentRequestDTO;
import com.example.homework.contents.dto.CreateContentResponseDTO;
import com.example.homework.contents.dto.UpdateContentRequestDTO;
import com.example.homework.contents.dto.UpdateContentResponseDTO;
import com.example.homework.contents.entity.Content;
import com.example.homework.contents.repository.ContentRepository;
import com.example.homework.member.entity.Member;
import com.example.homework.member.repository.MemberRepository;
import com.example.homework.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final MemberRepository memberRepository;
    private final Validation validation;

    @Transactional
    public CreateContentResponseDTO createContent(CreateContentRequestDTO requestDTO, Long memberId){

        Member member = validation.memberValidation(memberId);

        Content content = Content.from(requestDTO.getTitle(), requestDTO.getDescription(), member);
        Content saveContent = contentRepository.save(content);

        return CreateContentResponseDTO.from(saveContent);

    }

    @Transactional
    public UpdateContentResponseDTO updateContent(UpdateContentRequestDTO requestDTO, Long memberId) {

        Content content = validation.contentValidation(requestDTO.getId(),  memberId);

        content.update(requestDTO);

        return UpdateContentResponseDTO.from(
                content.getId(),
                content.getTitle(),
                content.getDescription()
        );
    }

    @Transactional
    public Long deleteContent(Long contentId ,Long memberId) {

        Content content = validation.contentValidation(contentId,  memberId);

        contentRepository.delete(content);

        return contentId;
    }

    @Transactional(readOnly = true)
    public void SearchContentList() {

    }

    @Transactional(readOnly = true)
    public void searchContent(Long memberId) {

    }


}
