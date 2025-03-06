package com.example.demo.service;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostSpecification;
import com.example.demo.dto.PostUpdateRequest;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ImageService imageService;

    public Page<Post> findAllPosts(String type, String keyword, int page) {
        Specification<Post> spec = Specification.where(PostSpecification.hasType(type))
                .and(PostSpecification.hasKeyword(keyword));

        Pageable pageable = PageRequest.of(page-1, 10);
        return postRepository.findAll(spec, pageable);
    }

    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 않습니다."));
        if (post != null) {
            post.delete();
        }
    }

    @Transactional
    public Post update(Long postId, PostUpdateRequest request) throws IOException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Optional<String> imageUrl = imageService.uploadPostImage(request.getImage());
        post.update(request);
        imageUrl.ifPresent(post::updatePostUrl);
        return post;
    }

    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
