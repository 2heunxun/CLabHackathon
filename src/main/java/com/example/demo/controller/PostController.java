package com.example.demo.controller;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostType;
import com.example.demo.dto.PostUpdateRequest;
import com.example.demo.dto.PostUpdateResponse;
import com.example.demo.dto.SimplePostDTO;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 리스트 조회
     */
    @GetMapping("/post")
    public String getPostList(Model model) {
        List<Post> allPosts = postService.findAllPosts();
        List<SimplePostDTO> result = allPosts.stream()
                .map(o -> new SimplePostDTO(o))
                .collect(toList());
        model.addAttribute("allPosts", result);
        return "posts/postList";
    }

    /**
     * 포스트 상세 조회
     */
    @GetMapping("/post/{postId}")
    public String getPost(@PathVariable Long postId, Model model) {
        Post post = postService.findPostById(postId);
        model.addAttribute("post", post);
        return "posts/post";
    }

    //포스트 생성
    @PostMapping("/post")
    public String postCreate(@RequestParam("type") PostType type,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("price") int price) {
        postService.save(type,title,content,price);
        return "redirect:/post";
    }


    //포스트 일부 수정
    @PutMapping("/post/{postId}")
    public String editPost(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateRequest request) {

        postService.update(postId,request);
        return "redirect:/post/" + postId;
    }

//    //포스트 전체 수정
//    @PatchMapping("posts/{postId}")
//    public PostDTO updatePost(@PathVariable("postId") Long postId, Model model) {
//        Post post = postService.findPostById(postId);
//        return new PostDTO(post);
//    }

    //포스트 삭제
    @DeleteMapping("/post/{postId}")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.delete(postId);
        return "redirect:/post";
    }
}
