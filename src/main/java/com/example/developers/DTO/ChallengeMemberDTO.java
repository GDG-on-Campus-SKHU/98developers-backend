package com.example.developers.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChallengeMemberDTO {
    MemberDTO member;

    String image;

    Boolean success;
}
