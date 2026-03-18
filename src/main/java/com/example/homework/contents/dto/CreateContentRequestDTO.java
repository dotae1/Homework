package com.example.homework.contents.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateContentRequestDTO {

    @NotNull
    private String title;
    @NotNull
    private String description;
}
