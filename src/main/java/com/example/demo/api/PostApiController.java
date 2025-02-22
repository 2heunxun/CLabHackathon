package com.example.demo.api;

import com.example.demo.domain.post.Post;
import com.example.demo.dto.PostDTO;
import com.example.demo.dto.PostUpdateRequest;
import com.example.demo.dto.PostUpdateResponse;
import com.example.demo.dto.SimplePostDTO;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    //포스트 전체 조회
    @GetMapping("api/post")
    public List<SimplePostDTO> getPosts() {
        List<Post> allPosts = postService.findAllPosts();
        List<SimplePostDTO> result = allPosts.stream()
                .map(o -> new SimplePostDTO(o))
                .collect(toList());
        return result;
    }

    //포스트 상세 조회
    @GetMapping("api/post/{postId}/")
    public PostDTO post(@PathVariable("postId") Long postId) {
        Post post = postService.findPostById(postId);
        return new PostDTO(post);
    }

    //포스트 일부 수정
    @PutMapping("api/post/{postId}")
    public PostUpdateResponse editPost(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateRequest request) {

        postService.update(postId,request);

        Post post = postService.findPostById(postId);
        return new PostUpdateResponse(post);
    }

//    //포스트 전체 수정
//    @PatchMapping("posts/{postId}")
//    public PostDTO updatePost(@PathVariable("postId") Long postId, Model model) {
//        Post post = postService.findPostById(postId);
//        return new PostDTO(post);
//    }

    //포스트 삭제
    @DeleteMapping("api/post/{postId}")
    public void deletePost(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPostById(postId);
        postService.delete(post);
    }

}
