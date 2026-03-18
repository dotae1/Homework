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
import com.example.homework.member.entity.Role;
import com.example.homework.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
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

        Role role = validation.memberRoleValidation(memberId);
        Content content;
        if(role == Role.USER){
            content = validation.contentValidation(requestDTO.getId(),  memberId);
            content.update(requestDTO);
        } else if(role == Role.ADMIN){
            content = validation.contentFoundValidation(requestDTO.getId());
            content.adminUpdate(requestDTO);
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
    public Long deleteContent(Long contentId ,Long memberId) {

        Role role = validation.memberRoleValidation(memberId);
        Content content;

        if(role == Role.USER){
            content = validation.contentValidation(contentId, memberId);
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
    public void SearchContentList(Long memberId) {

        //회원만 조회할 수 있다는 가정하의 설계
        Member member = validation.memberValidation(memberId);



    }

    @Transactional(readOnly = true)
    public void searchContent(Long memberId) {

        //회원만 조회할 수 있다는 가정하의 설계
        Member member = validation.memberValidation(memberId);

    }


}
