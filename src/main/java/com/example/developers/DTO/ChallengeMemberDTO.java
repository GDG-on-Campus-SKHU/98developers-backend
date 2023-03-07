package com.example.developers.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ChallengeMemberDTO {
    MemberDTO member;

    String image;

    Boolean isSuccess;
}
