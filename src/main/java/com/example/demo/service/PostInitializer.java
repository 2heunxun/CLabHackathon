package com.example.demo.service;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PostInitializer implements ApplicationRunner {

    @Autowired
    private final PostService postService;

    public PostInitializer(PostService postService) {
        this.postService = postService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post1 = new Post(PostType.BOOK, "개발의 정석 팔아요", "빠르게 팔아보겠습니다.", 10000, "");
        Post post2 = new Post(PostType.BOOK, "리눅스 마스터 팝니다", "리눅스 토르발즈는 이렇게 말했죠.", 20000, "");
        Post post3 = new Post(PostType.BOOK, "테스트용 제목", "테스트용 내용", 40000, "");

        postService.save(post1);
        postService.save(post2);
        postService.save(post3);
    }
}
