package com.example.demo.Controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.LoginDTO;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final MemberService memberService;

    @GetMapping
    public String loginPage() {
        return "login"; // login.html로 이동
    }

    @PostMapping
    public String login(@ModelAttribute LoginDTO loginDTO, HttpSession session, Model model) {
        Optional<Member> member = memberService.login(loginDTO);

        if (member.isPresent()) {
            session.setAttribute("loginMember", member.get());
            return "redirect:/mypage"; // 로그인 성공 시 마이페이지로 이동
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "login"; // 로그인 실패 시 다시 로그인 페이지
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
