package com.example.homework.contents.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateContentRequestDTO {
    private Long id;

    @NotBlank (message = "제목은 필수 입니다.")
    private String title;
    @NotBlank (message = "내용은 필수입니다.")
    private String description;

}
