package com.example.homework.contents.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateContentRequestDTO {
    private Long id;

    @NotBlank (message = "제목은 필수 입니다.")
    @Size(max = 50, message = "제목은 50자 이내로 입력해주세요.")
    private String title;
    @NotBlank (message = "내용은 필수입니다.")
    private String description;

}
