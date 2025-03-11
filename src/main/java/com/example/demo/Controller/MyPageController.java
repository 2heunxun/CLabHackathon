package com.example.demo.Controller;

import com.example.demo.domain.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @GetMapping
    public String myPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("loginMember");
        if (member == null) {
            return "redirect:/login"; // 로그인 안 되어 있으면 로그인 페이지로 이동
        }
        model.addAttribute("member", member);
        return "mypage"; // 마이페이지로 이동
    }
}
