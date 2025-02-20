package com.example.demo.service;

import com.example.demo.domain.post.Post;
import com.example.demo.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public Post findPostById(Long id) {
        return postRepository.findOne(id);
    }

}
