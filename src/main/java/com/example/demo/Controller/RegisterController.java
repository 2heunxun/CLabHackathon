package com.example.demo.Controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {
    private final MemberService memberService;

    @GetMapping
    public String register() {
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute RegisterDTO registerDTO, Model model){
        try{
            memberService.registerMember(registerDTO);
            return "redirect:/login";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
