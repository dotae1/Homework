package com.example.homework.contents.dto;

import lombok.Getter;

import java.awt.print.Pageable;

@Getter
public class UpdateContentRequestDTO {
    private Long id;
    private String title;
    private String description;

}
