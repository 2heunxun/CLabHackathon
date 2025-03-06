package com.example.demo.Controller;

import com.example.demo.domain.post.Post;
import com.example.demo.dto.PostRequestDTO;
import com.example.demo.dto.SimplePostDTO;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class HomeController {

    private final PostService postService;

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

    @GetMapping("/post")
    public String createPost(Model model) {
        //현재 로그인한 회원의 정보를 가져오는 로직 추가 후 살릴 예정입니다.
        //model.addAttribute("postDto", new PostRequestDTO());
        //return "createPost";
        return "";
    }

    @PostMapping("/post")
    public String createPost(@ModelAttribute PostRequestDTO postRequestDTO) {
        return "redirect:/post";
    }


}
