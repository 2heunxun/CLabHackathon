package com.example.demo.api;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostType;
import com.example.demo.service.PostService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    //포스트 전체 조회
    @GetMapping("posts")
    public List<SimplePostDTO> getPosts() {
        List<Post> allPosts = postService.findAllPosts();
        List<SimplePostDTO> result = allPosts.stream()
                .map(o -> new SimplePostDTO(o))
                .collect(toList());
        return result;
    }

    @Data
    public static class SimplePostDTO {
        private String title;
        private int price;

        public SimplePostDTO(Post post) {
            this.title = post.getTitle();
            this.price = post.getPrice();
        }
    }

    //포스트 상세 조회
    @GetMapping("posts/{postId}/")
    public PostDTO post(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPostById(postId);
        return new PostDTO(post);
    }

    //포스트 일부 수정
    @PutMapping("posts/{postId}")
    public PostDTO updatePost(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPostById(postId);
        return new PostDTO(post);
    }

    //포스트 전체 수정
    @PatchMapping("posts/{postId}")
    public PostDTO editPost(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPostById(postId);
        return new PostDTO(post);
    }

    //포스트 삭제
    @DeleteMapping("posts/{postId}")
    public void deletePost(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPostById(postId);
        postService.delete(post);
    }

    @Data
    public static class PostDTO {
        @Enumerated(EnumType.STRING)
        private PostType type;

        //private String userName;

        private String title;
        private String content;
        private int price;
        private LocalDateTime createdAt;
        private String ThumbnailUrl;

        public PostDTO(Post post) {
            this.type = post.getType();
            //this.userName = post.getUser().getName();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.price = post.getPrice();
            this.createdAt = post.getCreatedAt();
            this.ThumbnailUrl = post.getThumbnailUrl();
        }
    }

}
