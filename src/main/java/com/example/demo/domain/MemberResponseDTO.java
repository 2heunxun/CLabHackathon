package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDTO {
    private Long memberId;
    private String userId;
    private String nickname;
    private String userProfile;
    private String messageId;
}
