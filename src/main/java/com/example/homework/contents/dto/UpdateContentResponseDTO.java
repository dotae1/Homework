package com.example.homework.contents.dto;

import lombok.Getter;

@Getter
public class UpdateContentResponseDTO {

    private Long id;
    private String title;
    private String description;

    private UpdateContentResponseDTO(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public static UpdateContentResponseDTO from(Long id, String title, String description) {
        return new UpdateContentResponseDTO(id, title, description);
    }

}
