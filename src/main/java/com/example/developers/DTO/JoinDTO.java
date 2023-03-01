package com.example.developers.DTO;

import com.example.developers.domain.Member;
import lombok.Data;

import java.util.Collections;

@Data
public class JoinDTO {
    private String userName;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .userName(userName)
                .roles(Collections.singletonList("USER"))
                .build();
    }
}