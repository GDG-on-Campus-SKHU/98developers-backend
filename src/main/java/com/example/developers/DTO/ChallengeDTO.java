package com.example.developers.DTO;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDTO {

    private Long id;

    private String topic; // 주제

    private String howProof; // 인증 방법

    private String expectedResults; // 기대 효과

    private String pleaseNote; // 주의 사항

    private String correctProof; // 올바른 예시 이미지

    private Date periodStartDate; // 인증 시작 date

    private Date periodEndDate; // 인증 마지막 date

    private Date expiredDay; // 참가 신청 마지막 date

    private List<ChallengeMemberDTO> members;

}
