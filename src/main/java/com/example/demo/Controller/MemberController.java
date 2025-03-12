package com.example.demo.Controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.MemberService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    //Create

    //Research
    // 수정필요 -> 반환에 대한 DTO 생성후 비밀번호를 제외한 반환이 필요 ! -> 보안상 문제가 발생
    @GetMapping("/{userId}")
    public ResponseEntity<MemberResponseDTO> getMember(@PathVariable String userId) {
        Optional<Member> member = memberService.findByUserId(userId);
        return member.map(m -> ResponseEntity.ok(new MemberResponseDTO(
                m.getMemberId(),
                m.getUserId(),
                m.getNickname(),
                m.getUserProfile(),
                m.getMessageId()
        ))).orElseGet(()-> ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }


}
