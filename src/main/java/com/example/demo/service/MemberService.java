package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.dto.UpdateMemberDTO;
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

    public Member registerMember(RegisterDTO registerDTO) {
        // userProfile이 null이면 기본값을 설정
        String userProfile ="https://storage.googleapis.com/hackathon1jokbostore/KakaoTalk_20250312_184655252.png";

        Member member = Member.builder()
                .userId(registerDTO.getUserId())
                .password(registerDTO.getPassword())
                .nickname(registerDTO.getNickname())
                .messageId(registerDTO.getMessageId())
                .userProfile(userProfile) // 기본값을 적용
                .build();

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

    @Transactional
    public Member updateMember(Long memberId, UpdateMemberDTO updateDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        // 모두 변경
        member = Member.builder()
                .memberId(member.getMemberId()) // 기존 ID 유지
                .userId(member.getUserId()) // 기존 아이디 유지
                .password(updateDTO.getPassword() != null && !updateDTO.getPassword().isEmpty()
                        ? updateDTO.getPassword() // 비밀번호가 있을 경우 그대로 저장
                        : member.getPassword()) // 비밀번호가 없으면 기존 비밀번호 유지
                .nickname(updateDTO.getNickname()) // 새로운 닉네임
                .messageId(updateDTO.getMessageId()) // 새로운 카카오톡 ID
                .build();

        return memberRepository.save(member);
    }


    public Optional<Member> login(LoginDTO loginDTO) {
        return memberRepository.findByUserId(loginDTO.getUserId())
                .filter(member -> member.getPassword().equals(loginDTO.getPassword())); // 비밀번호 검증
    }
}
