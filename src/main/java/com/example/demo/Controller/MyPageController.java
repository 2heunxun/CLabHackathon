package com.example.demo.Controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.UpdateMemberDTO;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    private MemberService memberService;

    @GetMapping
    public String myPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");
        if (member == null) {
            return "redirect:/login"; // 로그인 안 되어 있으면 로그인 페이지로 이동
        }
        model.addAttribute("member", member);
        return "mypage"; // 마이페이지로 이동
    }

    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute UpdateMemberDTO updateDTO, HttpSession session) {
        Member member = (Member) session.getAttribute("loginMember");

        if (member == null) {
            return "redirect:/login"; // 로그인 안 되어 있으면 로그인 페이지로 이동
        }

        // 회원 정보 업데이트
        Member updatedMember = memberService.updateMember(member.getMemberId(), updateDTO);

        // 세션 정보 업데이트
        session.setAttribute("loginMember", updatedMember);

        return "redirect:"; // 수정 완료 후 마이페이지 이동
    }

    @GetMapping("/edit")
    public String editProfile(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");
        if (member == null) {
            return "redirect:/login"; // 로그인 안 되어 있으면 로그인 페이지로 이동
        }
        model.addAttribute("member", member);
        return "mypage_edit"; // 내 정보 수정 페이지로 이동
    }
}
