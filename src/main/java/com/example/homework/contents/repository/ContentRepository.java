package com.example.homework.contents.repository;

import com.example.homework.contents.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository  extends JpaRepository<Content, Long> {
    Optional<Content> findByIdAndMemberId(Long contentId, Long memberId);
}
