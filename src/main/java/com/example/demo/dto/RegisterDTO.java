package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    private String userId;
    private String password;
    private String nickname;
    private String messageId;
}
