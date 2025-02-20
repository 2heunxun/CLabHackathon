package com.example.demo.controller;

import com.example.demo.domain.post.Post;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 삽입
     */


    /**
     * 조회
     */
    @PostMapping("/posting/{postId}/")
    public String post(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPostById(postId);
        model.addAttribute("post", post);
        return "post";
    }

    /**
     * 수정
     */

    /**
     * 삭제
     */
}
