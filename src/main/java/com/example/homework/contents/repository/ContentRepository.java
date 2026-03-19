package com.example.homework.contents.repository;

import com.example.homework.contents.dto.SearchContentSummariesDTO;
import com.example.homework.contents.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository  extends JpaRepository<Content, Long> {
    Optional<Content> findByIdAndMember_LoginId(Long id, String loginId);

    @Query("select new com.example.homework.contents.dto.SearchContentSummariesDTO(c.id, c.title, c.description, c.viewCount, c.createdBy, c.createdDate) " +
            "from Content c left join c.member m")
    Page<SearchContentSummariesDTO> findBySummeries(Pageable pageable);

}
