package com.example.demo.domain;

import com.example.demo.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="member")
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId; // member 구분 아이디

    @Column(unique=true, nullable=false, length = 20, name = "user_id")
    private String userId; // 아이디

    @Column(nullable=false)
    private String password; //비밀번호

    @Column(unique=true, nullable=false, length = 20)
    private String nickname; //닉네임

    @Column
    private String userProfile; // 기본 프로필 사진 url

    @Column(unique=true, nullable=false)
    private String messageId; //카카오톡 아이디



}
