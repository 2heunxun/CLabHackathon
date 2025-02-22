package com.example.demo.repository;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostType;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository{
    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public Post findOne(Long id) {return em.find(Post.class,id);}

    public List<Post> findAll() {
        return em.createQuery("select p from Post p where p.isDeleted = false", Post.class).getResultList();
    }

    public List<Post> findByTag(PostType type) {
        return em.createQuery("select p from Post p where p.type = :type and p.isDeleted = false", Post.class)
                .setParameter("type",type)
                .getResultList();
    }

    public List<Post> findByTitle(String title) {
        return em.createQuery("select p from Post p where p.title = :title and p.isDeleted = false", Post.class)
                .setParameter("title","%" + title + "%")
                .getResultList();
    }

    public List<Post> findByContent(String content) {
        return em.createQuery("select p from Post p where p.content = :content and p.isDeleted = false", Post.class)
                .setParameter("content","%" + content + "%")
                .getResultList();
    }

}
