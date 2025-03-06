package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostType;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PostInitializer implements ApplicationRunner {
    private final PostService postService;
    private final MemberService memberService;

    private final PostRepository postRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(postRepository.count() > 0) {
            log.info("이미 테스트할만한 데이터가 있습니다.");
            return;
        }

        Member member = Member.builder()
                .userId("testUser123")
                .password("securePassword")
                .nickname("TestNickname")
                .userProfile("https://example.com/default-profile.jpg")
                .messageId("kakao12345")// 변경
                .build();

        memberService.saveMember(member);

        Post post1 = Post.builder()
                .type(PostType.BOOK)
                .author(member)
                .title("코딩의 정석")
                .content("ㅁㄴㅇㄹ")
                .price(10000)
                .thumbnailUrl("https://storage.cloud.google.com/hackathon1jokbostore/8c38f6f9-c704-4794-a85f-989aa1079550")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        Post post2 = Post.builder()
                .type(PostType.BOOK)
                .author(member)
                .title("리눅스 마스터 팝니다")
                .content("ㄹㄹㄹㄹ")
                .price(10000)
                .thumbnailUrl("https://storage.cloud.google.com/hackathon1jokbostore/8c38f6f9-c704-4794-a85f-989aa1079550")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        Post post3 = Post.builder()
                .type(PostType.BOOK)
                .author(member)
                .title("테스트용 서적")
                .content("ㅇㅇㅇㅇ")
                .price(40000)
                .thumbnailUrl("https://storage.cloud.google.com/hackathon1jokbostore/8c38f6f9-c704-4794-a85f-989aa1079550")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        postService.save(post1);
        postService.save(post2);
        postService.save(post3);
    }
}