package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UpdateMemberDTO {
    private String nickname;
    private String messageId;
    private String password;
}
