package com.example.demo.Controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberResponseDTO;
import com.example.demo.domain.post.Post;
import com.example.demo.dto.PostRequestDTO;
import com.example.demo.dto.SimplePostDTO;
import com.example.demo.service.ImageService;
import com.example.demo.service.MemberService;
import com.example.demo.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class HomeController {

    private final PostService postService;
    private final MemberService memberService;
    private final ImageService imageService;

    @GetMapping
    public String getPostList(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "1")int page, Model model) {

        Page<Post> allPosts = postService.findAllPosts(type,keyword,page);
        List<SimplePostDTO> result = allPosts.stream()
                .map(SimplePostDTO::new)
                .collect(Collectors.toList());

        model.addAttribute("allPosts", result);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", allPosts.getTotalPages());

        return "mobile";
    }


    @GetMapping("/mobile")
    public String goMobile(Model model) {
        String type = "";
        String keyword = "";
        int page = 1;

        Page<Post> allPosts = postService.findAllPosts(type,keyword,page);
        List<SimplePostDTO> result = allPosts.stream()
                .map(SimplePostDTO::new)
                .collect(Collectors.toList());

        model.addAttribute("allPosts", result);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", allPosts.getTotalPages());
        return "mobile";
    }
}
