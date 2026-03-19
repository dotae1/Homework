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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<?> createContent(
            @RequestBody @Valid CreateContentRequestDTO requestDTO,
            @AuthenticationPrincipal UserDetails userDetails
            ) {

        CreateContentResponseDTO responseDTO = contentService.createContent(requestDTO, userDetails.getUsername());
        return SuccessResponseEntity.toResponseEntity(SuccessCode.CONTENT_CREATED, responseDTO);

    }

    @PatchMapping("/patch")
    public ResponseEntity<SuccessResponseEntity<UpdateContentResponseDTO>> updateContent(
            @RequestBody @Valid UpdateContentRequestDTO requestDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UpdateContentResponseDTO responseDTO = contentService.updateContent(requestDTO, userDetails.getUsername());
        return SuccessResponseEntity.toResponseEntity(SuccessCode.CONTENT_UPDATED, responseDTO);
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<SuccessResponseEntity<Long>> deleteContent(
            @PathVariable Long contentId,
            @AuthenticationPrincipal UserDetails userDetails) {
        Long deletedId = contentService.deleteContent(contentId, userDetails.getUsername());

        return SuccessResponseEntity.toResponseEntity(SuccessCode.CONTENT_SUCCESS_DELETE,deletedId);
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
            @PathVariable Long contentId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        SearchContentDetailResponseDTO responseDTO = contentService.searchContent(contentId, userDetails.getUsername());

        return SuccessResponseEntity.toResponseEntity(SuccessCode.CONTENT_SUCCESS_SEARCH, responseDTO);
    }

}
