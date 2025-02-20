package com.example.demo.domain.post;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
public class Post {
    @Id
    @GeneratedValue
    @Column(name="post_id")
    private long id;

    @Enumerated(EnumType.STRING)
    private PostType type;

    //private User user;

    private String title;
    private String content;
    private int price;
    private LocalDateTime createdAt;
    private String ThumbnailUrl;
}