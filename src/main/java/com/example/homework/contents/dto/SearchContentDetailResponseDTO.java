package com.example.homework.contents.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SearchContentDetailResponseDTO {

    private Long id;
    private String title;
    private String discription;
    private LocalDateTime createdDate;
    private String createdBy;
    private String lastModifiedBy;
    private Integer viewCount;

    public static SearchContentDetailResponseDTO from(
            Long id,
            String title,
            String discription,
            LocalDateTime createdDate,
            String createdBy,
            String lastModifiedBy,
            Integer viewCount
    ) {
        return SearchContentDetailResponseDTO.builder()
                .id(id)
                .title(title)
                .discription(discription)
                .createdDate(createdDate)
                .createdBy(createdBy)
                .lastModifiedBy(lastModifiedBy)
                .viewCount(viewCount)
                .build();
    }

}
