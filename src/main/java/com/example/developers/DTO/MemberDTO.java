package com.example.developers.DTO;

import com.example.developers.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@Builder
public class MemberDTO {
    private String uid;
    private String email;
    private String name;
    private String avatar;
}
