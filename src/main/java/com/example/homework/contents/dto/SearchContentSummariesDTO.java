package com.example.homework.contents.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchContentSummariesDTO {

    private Long id;
    private String title;
    private String content;
    private Long viewCount;
    private String createBy;
    private LocalDateTime createdDate;

}
