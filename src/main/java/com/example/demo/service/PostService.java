package com.example.demo.service;

import com.example.demo.domain.post.Post;
import com.example.demo.dto.PostUpdateRequest;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Post findPostById(Long id) {
        return postRepository.findOne(id);
    }
    

    @Transactional
    public void delete(Post post) {
    }

    @Transactional
    public void update(Long postId, PostUpdateRequest request) {
        Post post = postRepository.findOne(postId);
        post.update(request);
    }
}
