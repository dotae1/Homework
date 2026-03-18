package com.example.homework.contents.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchContentListDTO {

    private List<SearchContentSummariesDTO> contentList;
}
