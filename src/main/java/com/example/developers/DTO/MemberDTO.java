package com.example.developers.DTO;

import com.example.developers.domain.Member;
import lombok.Data;

@Data
public class MemberDTO {
    private String uid;
    private String email;
    private String name;
    private String avatar;

    public MemberDTO(Member member) {
        this.uid = member.getUsername();
        this.email = member.getEmail();
        this.name = member.getName();
        this.avatar = member.getAvatar();
    }
}
