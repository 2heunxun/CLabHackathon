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

    @GetMapping("/post")
    public String createPost(HttpSession session, Model model) {
        // 세션에서 로그인한 사용자 정보 가져오기
        Member member = (Member) session.getAttribute("loginMember");
        // 로그인이 안 되어 있으면 로그인 페이지로 리다이렉트
        if (member == null) {
            return "redirect:/login";
        }
        model.addAttribute("member", member);
        model.addAttribute("postDto", new PostRequestDTO());
        return "createPost";
    }


    @PostMapping("/post")
    public String createPost(HttpSession session, @ModelAttribute PostRequestDTO postRequestDTO) throws IOException {

        Member author = (Member) session.getAttribute("loginMember");
        // 로그인이 안 되어 있으면 로그인 페이지로 리다이렉트
        if (author == null) {
            return "redirect:/login";
        }
        Optional<String> image = imageService.uploadPostImage(postRequestDTO.getThumbnailImage());

        Post post = Post.builder()
                .type(postRequestDTO.getType())
                .author(author)
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent())
                .price(postRequestDTO.getPrice())
                .thumbnailUrl(image.orElse(null))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        Post savedPost = postService.save(post);
        return "redirect:/mobile";
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
