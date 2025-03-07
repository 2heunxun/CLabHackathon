package com.example.demo.Controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.post.Post;
import com.example.demo.dto.PostRequestDTO;
import com.example.demo.dto.SimplePostDTO;
import com.example.demo.service.MemberService;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class HomeController {

    private final PostService postService;
    private final MemberService memberService;

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

        //로그인한 유저를 넣어주는 코드가 필요합니다.
        // 테스트가 불가능해 관련한 코드가 추가 되면 createPost쪽 추가 수정 하겠습니다.
        //Member member = memberService.findByUserId();
        Optional<Member> member = memberService.findByUserId("test123");
        model.addAttribute("member", member.orElse(null));
        model.addAttribute("postDto", new PostRequestDTO());
        return "createPost";
    }

    @PostMapping("/post")
    public String createPost(@ModelAttribute PostRequestDTO postRequestDTO) {
        return "redirect:/post";
    }


}
