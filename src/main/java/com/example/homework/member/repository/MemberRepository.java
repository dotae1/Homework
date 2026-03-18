package com.example.homework.member.repository;

import com.example.homework.contents.entity.Content;
import com.example.homework.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberRole(Long memberId);

}
