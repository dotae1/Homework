package com.example.homework.contents.controller;

import com.example.homework.contents.dto.*;
import com.example.homework.exception.SuccessCode;
import com.example.homework.exception.SuccessResponseEntity;
import com.example.homework.contents.service.ContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<SuccessResponseEntity<CreateContentResponseDTO>> createContent(
            @RequestBody @Valid CreateContentRequestDTO requestDTO
            ) {
        Long memberId = 1L;
        CreateContentResponseDTO responseDTO = contentService.createContent(requestDTO, memberId);
        return SuccessResponseEntity.toResponseEntity(SuccessCode.CONTENT_CREATED, responseDTO);
    }

    @PatchMapping("/{contentId}")
    public ResponseEntity<SuccessResponseEntity<UpdateContentResponseDTO>> updateContent(
            @RequestBody @Valid UpdateContentRequestDTO requestDTO
    ) {
        Long memberId = 2L;
        UpdateContentResponseDTO responseDTO = contentService.updateContent(requestDTO, memberId);
        return SuccessResponseEntity.toResponseEntity(SuccessCode.CONTENT_UPDATED, responseDTO);
    }

    @DeleteMapping("/{contentId}")
    public Long deleteContent(
            @PathVariable Long contentId) {
        Long memberId = 2L;
        return contentService.deleteContent(contentId, memberId);
    }

    @GetMapping("/search")
    public ResponseEntity<SuccessResponseEntity<SearchContentListResponseDTO>> searchContent(
            @PageableDefault(size = 5, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        SearchContentListResponseDTO searchContentListResponseDTO = contentService.SearchContentList(pageable);

        return SuccessResponseEntity.toResponseEntity(SuccessCode.CONTENT_LIST_SUCCESS_SEARCH, searchContentListResponseDTO);
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<SuccessResponseEntity<SearchContentDetailResponseDTO>> contentDetail (
            @PathVariable Long contentId
    ) {
        Long memberId = 1L;
        SearchContentDetailResponseDTO responseDTO = contentService.searchContent(contentId, memberId);

        return SuccessResponseEntity.toResponseEntity(SuccessCode.CONTENT_SUCCESS_SEARCH, responseDTO);
    }

}
