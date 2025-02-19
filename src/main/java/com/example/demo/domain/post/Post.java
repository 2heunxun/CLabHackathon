package com.example.demo.domain.post;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type")
@Getter
public class Post {
    @Id
    @GeneratedValue
    @Column(name="post_id")
    private long id;

    private String title;
    private String content;
    private int price;
    private LocalDateTime createdAt;
    private String ThumbnailUrl;
}