package com.example.demo.Controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.post.Post;
import com.example.demo.dto.PostRequestDTO;
import com.example.demo.dto.PostResponseDTO;
import com.example.demo.dto.PostUpdateRequest;
import com.example.demo.dto.SimplePostDTO;
import com.example.demo.service.ImageService;
import org.springframework.http.ResponseEntity;
import com.example.demo.service.MemberService;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final ImageService imageService;

    //포스트 생성
    @PostMapping("")
    public ResponseEntity<PostResponseDTO> postCreate(@ModelAttribute PostRequestDTO request) throws IOException {
        Optional<Member> author = memberService.findByUserId(request.getAuthorId());
        Optional<String> image = imageService.uploadPostImage(request.getThumbnailImage());

        Post post = Post.builder()
                .type(request.getType())
                .author(author.orElse(null))
                .title(request.getTitle())
                .content(request.getContent())
                .price(request.getPrice())
                .thumbnailUrl(image.orElse(null))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        Post savedPost = postService.save(post);

        return ResponseEntity.ok(new PostResponseDTO(savedPost));
    }

    //포스트 리스트 조회
    @GetMapping
    public ResponseEntity<List<SimplePostDTO>> getPostList(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "0")int page) {

        List<Post> allPosts = postService.findAllPosts(type,keyword,page);
        List<SimplePostDTO> result = allPosts.stream()
                .map(SimplePostDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    //포스트 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long postId) {
        Optional<Post> post = postService.findPostById(postId);
        Optional<PostResponseDTO> postResponseDTO = post.map(PostResponseDTO::new);
        return postResponseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //포스트 수정 폼
    @GetMapping("/editform/{postId}")
    public ResponseEntity<PostResponseDTO> editPostForm(@PathVariable Long postId) {
        Optional<Post> post = postService.findPostById(postId);
        Optional<PostResponseDTO> postResponseDTO = post.map(PostResponseDTO::new);
        return postResponseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());  // 수정 폼으로 이동
    }


    //포스트 일부 수정
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateRequest request) throws IOException {
        Post updatePost = postService.update(postId,request);
        return ResponseEntity.ok(updatePost);
    }

//    //포스트 전체 수정
//    @PatchMapping("posts/{postId}")
//    public PostDTO updatePost(@PathVariable("postId") Long postId, Model model) {
//        Post post = postService.findPostById(postId);
//        return new PostDTO(post);
//    }

    //포스트 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Long postId) {
        postService.delete(postId);
        return ResponseEntity.noContent().build();
    }
}
