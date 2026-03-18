package com.example.homework.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SearchContentSummariesDTO {

    private Long id;
    private String title;
    private String description;
    private Integer viewCount;
    private String createBy;
    private LocalDateTime createdDate;

}
