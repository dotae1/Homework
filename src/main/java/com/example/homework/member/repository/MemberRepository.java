package com.example.homework.member.repository;

import com.example.homework.member.entity.Member;
import com.example.homework.member.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("select m.role from Member m WHERE m.loginId = :loginId")
    Optional<Role> findRoleById(String loginId);

    boolean existsByLoginId(String loginId);

    Optional<Member> findByLoginId(String loginId);

    boolean existsByNickname(String nickname);



}
