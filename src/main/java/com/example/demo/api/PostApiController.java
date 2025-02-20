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

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @GetMapping("posts/{postId}/")
    public PostDTO post(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPostById(postId);
        return new PostDTO(post);
    }


    @PutMapping("posts/{postId}")
    public PostDTO updatePost(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPostById(postId);
        return new PostDTO(post);
    }

    @PatchMapping("posts/{postId}")
    public PostDTO editPost(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPostById(postId);
        return new PostDTO(post);
    }

    @DeleteMapping("posts/{postId}")
    public void deletePost(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findPostById(postId);
        postService.delete(post);
    }

    @Data
    public class PostDTO {
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
