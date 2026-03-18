package com.example.homework.contents.controller;

import com.example.homework.contents.dto.UpdateContentRequestDTO;
import com.example.homework.contents.dto.UpdateContentResponseDTO;
import com.example.homework.exception.SuccessCode;
import com.example.homework.exception.SuccessResponseEntity;
import com.example.homework.contents.dto.CreateContentRequestDTO;
import com.example.homework.contents.dto.CreateContentResponseDTO;
import com.example.homework.contents.service.ContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
            @RequestBody UpdateContentRequestDTO requestDTO
    ) {
        Long memberId = 1L;
        UpdateContentResponseDTO responseDTO = contentService.updateContent(requestDTO, memberId);
        return SuccessResponseEntity.toResponseEntity(SuccessCode.CONTENT_UPDATED, responseDTO);
    }

    @DeleteMapping("/{contentId}")
    public Long deleteContent(
            @PathVariable Long contentId,
            Long memberId) {
        return contentService.deleteContent(contentId, memberId);
    }

}
