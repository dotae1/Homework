package com.example.homework.contents.entity;

import com.example.homework.contents.dto.UpdateContentRequestDTO;
import com.example.homework.contents.dto.UpdateContentResponseDTO;
import com.example.homework.global.BaseEntity;
import com.example.homework.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "contents")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Column(name = "last_modified_by", nullable = false)
    private String lastModifiedBy;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    private Content(String title, String content, Member member) {
        this.title = title;
        this.description = content;
        this.lastModifiedBy = member.getNickname();
        this.createdBy = member.getNickname();
        this.member = member;
    }

    public static Content from(String title, String content, Member member) {
        return new Content(title, content, member);
    }

    public void update(UpdateContentRequestDTO requestDTO) {
        if(requestDTO.getTitle() != null) {
            this.title = requestDTO.getTitle();
        }
        if(requestDTO.getDescription() != null) {
            this.description = requestDTO.getDescription();
        }
    }

}
