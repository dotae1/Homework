package com.example.homework.contents.service;

import com.example.homework.contents.dto.*;
import com.example.homework.exception.CustomException;
import com.example.homework.exception.ErrorCode;
import com.example.homework.contents.entity.Content;
import com.example.homework.contents.repository.ContentRepository;
import com.example.homework.member.entity.Member;
import com.example.homework.member.entity.Role;
import com.example.homework.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final Validation validation;

    @Transactional
    public CreateContentResponseDTO createContent(CreateContentRequestDTO requestDTO, String loginId){
        Member member = validation.memberValidation(loginId);

        Content content = Content.from(requestDTO.getTitle(), requestDTO.getDescription(), member);
        Content saveContent = contentRepository.save(content);

        return CreateContentResponseDTO.from(saveContent);
    }

    @Transactional
    public UpdateContentResponseDTO updateContent(UpdateContentRequestDTO requestDTO, String loginId) {

        Role role = validation.memberRoleValidation(loginId);
        Content content;
        if(role == Role.USER){
            content = validation.contentValidation(requestDTO.getId(),  loginId);
            content.update(requestDTO);
        } else if(role == Role.ADMIN){
            Member admin = validation.memberValidation(loginId);

            content = validation.contentFoundValidation(requestDTO.getId());
            content.adminUpdate(requestDTO, admin.getNickname());
        } else {
            throw new CustomException(ErrorCode.MEMBER_BAD_REQUEST);
        }

        return UpdateContentResponseDTO.from(
                content.getId(),
                content.getTitle(),
                content.getDescription()
        );
    }

    @Transactional
    public Long deleteContent(Long contentId ,String loginId) {

        Role role = validation.memberRoleValidation(loginId);
        Content content;

        if(role == Role.USER){
            content = validation.contentValidation(contentId, loginId);
            contentRepository.delete(content);
        } else if(role == Role.ADMIN){
            content = validation.contentFoundValidation(contentId);
            contentRepository.delete(content);
        } else {
            throw new CustomException(ErrorCode.MEMBER_BAD_REQUEST);
        }

        return contentId;
    }

    @Transactional(readOnly = true)
    public SearchContentListResponseDTO SearchContentList(Pageable pageable) {

        Page<SearchContentSummariesDTO> page =
                contentRepository.findBySummaries(pageable);

        return SearchContentListResponseDTO.of(
                page.getContent()
        );



    }

    @Transactional
    public SearchContentDetailResponseDTO searchContent(Long contentId, String loginId) {

        //회원만 조회할 수 있다는 가정하의 설계
        Member member = validation.memberValidation(loginId);

        Content content = validation.contentFoundValidation(contentId);
        //조회가 이루어지면 조회수 증가
        content.upViewCount();

        return  SearchContentDetailResponseDTO.from(
                content.getId(),
                content.getTitle(),
                content.getDescription(),
                content.getCreatedDate(),
                content.getCreatedBy(),
                content.getLastModifiedBy(),
                content.getViewCount()
        );



    }


}
