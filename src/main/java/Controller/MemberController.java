package Controller;

import domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MemberService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    //Create
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member savedMember = memberService.saveMember(member);
        return ResponseEntity.ok(savedMember);
    }

    //Research
    @GetMapping("/{userId}")
    public ResponseEntity<Member> getMember(@PathVariable String userId) {
        Optional<Member> member = memberService.findByUserId(userId);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    //Update kakaoTalk 아이디
    @PutMapping
    public ResponseEntity<Member> updateMessageId(@PathVariable Long memberId, @RequestBody String newMessageId) {
        Member updateMember= memberService.updateMessageId(memberId, newMessageId);
        return ResponseEntity.ok(updateMember);
    }
}
