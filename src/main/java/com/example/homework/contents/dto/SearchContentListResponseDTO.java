package com.example.homework.contents.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchContentListResponseDTO {

    private List<SearchContentSummariesDTO> contentList;

    private SearchContentListResponseDTO(List<SearchContentSummariesDTO> contentList){
        this.contentList = contentList;
    }

    public static SearchContentListResponseDTO of(List<SearchContentSummariesDTO> contentList){
        return new SearchContentListResponseDTO(contentList);
    }
}
