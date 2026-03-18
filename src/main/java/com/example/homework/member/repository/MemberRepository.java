package com.example.homework.member.repository;

import com.example.homework.member.entity.Member;
import com.example.homework.member.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m.role from Member m WHERE m.memberId = :memberId")
    Optional<Role> findRoleById(Long memberId);

}
