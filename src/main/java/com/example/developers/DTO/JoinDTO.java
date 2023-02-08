package com.example.developers.DTO;

import com.example.developers.domain.Member;
import lombok.Data;

@Data
public class JoinDTO {
    private String userName;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .userName(userName)
                .password(password)
                .build();
    }
}