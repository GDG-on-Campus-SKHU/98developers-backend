package com.example.developers.DTO;

import com.example.developers.domain.Member;
import lombok.Data;

@Data
public class MemberDTO {
    private String username;
    private String email;
    private String name;
    private String picture;

    public MemberDTO(Member member) {
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.name = member.getName();
        this.picture = member.getPicture();
    }
}
