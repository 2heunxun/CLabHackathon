package com.example.demo.service;

import com.example.demo.domain.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //Create
    public Member saveMember(Member member){
        return memberRepository.save(member);
    }

    //Research
    public Optional<Member> findByUserId(String userId){
        return memberRepository.findByUserId(userId);
    }

    //Delete
    public void deleteMember(Long memberId){
        memberRepository.deleteById(memberId);
    }

    @Transactional //카카오 아이디 변경
    public Member updateMessageId(Long memberId, String newMessageId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        return memberRepository.save(
                Member.builder()
                        .memberId(member.getMemberId())
                        .userId(member.getUserId())
                        .password(member.getPassword())
                        .nickname(member.getNickname())
                        .userProfile(member.getUserProfile())
                        .messageId(newMessageId)// 변경
                        .build()
        );
    }
}
