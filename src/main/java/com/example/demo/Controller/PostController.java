package com.example.demo.Controller;


import com.example.demo.domain.Member;
import com.example.demo.domain.post.Post;
import com.example.demo.dto.PostRequestDTO;
import com.example.demo.service.ImageService;
import com.example.demo.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {

    private final ImageService imageService;
    private final PostService postService;

    @GetMapping
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


    @PostMapping
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

    @GetMapping("/{id}")
    public String getProductDetail(HttpSession session,@PathVariable Long id, Model model){
        Member user = (Member) session.getAttribute("loginMember");
        // 로그인이 안 되어 있으면 로그인 페이지로 리다이렉트
        if (user == null) {
            return "redirect:/login";
        }
        Post post = postService.findPostById(id).orElseThrow(()->new RuntimeException("해당 포스트를 찾을 수 없음."));

        model.addAttribute("isLoggedIn", user.getUserId().equals(post.getAuthor().getUserId()));
        model.addAttribute("post", post);
        return "product";
    }
}
