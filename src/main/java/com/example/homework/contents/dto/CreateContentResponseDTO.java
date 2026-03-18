package com.example.homework.contents.dto;

import com.example.homework.contents.entity.Content;
import lombok.Getter;

@Getter
public class CreateContentResponseDTO {

    private Long id;
    private String title;
    private String description;

    private CreateContentResponseDTO(Long id ,String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public static CreateContentResponseDTO from(Content content) {
        return new CreateContentResponseDTO(
                content.getId(),
                content.getTitle(),
                content.getDescription()
        );
    }
}
