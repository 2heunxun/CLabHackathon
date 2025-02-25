package service;

import post.Post;
import post.PostType;
import dto.PostUpdateRequest;
import repository.PostRepository;
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
    public void delete(Long postId) {
        Post post = postRepository.findOne(postId);
        if (post != null) {
            post.delete();
        }
    }

    @Transactional
    public void update(Long postId, PostUpdateRequest request) {
        Post post = postRepository.findOne(postId);
        post.update(request);
    }

    @Transactional
    public void save(PostType type,String title,String content,int price) {
        Post post = new Post(type,title,content,price);
        postRepository.save(post);
    }
}
