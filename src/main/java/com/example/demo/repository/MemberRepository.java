package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    void deleteById(Long memberId);
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByNickname(String nickname);
}