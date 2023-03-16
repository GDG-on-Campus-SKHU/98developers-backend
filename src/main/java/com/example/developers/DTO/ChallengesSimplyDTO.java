package com.example.developers.DTO;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengesSimplyDTO {
    private Long id;

    private String topic; // 주제

    private Date periodStartDate; // 인증 시작 date

    private Date periodEndDate; // 인증 마지막 date

    private Date expiredDay; // 참가 신청 마지막 date

    private int headCount;
}
