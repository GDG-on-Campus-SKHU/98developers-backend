package com.example.developers.domain;

import com.example.developers.DTO.ChallengeDTO;
import com.google.firebase.auth.FirebaseToken;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "challenge")
@Entity
public class Challenge {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String topic; // 주제

    @Column(name = "how_proof")
    private String howProof; // 인증 방법

    @Column(name = "expected_results")
    private String expectedResults; // 기대 효과

    @Column(name = "please_note")
    private String pleaseNote; // 주의 사항

    @Column(name = "correct_proof")
    private String correctProof; // 올바른 예시 이미지

    @Column(name = "period_start_date")
    private Date periodStartDate; // 인증 시작 date

    @Column(name = "period_end_date")
    private Date periodEndDate; // 인증 마지막 date

    @Column(name = "expired_day")
    private Date expiredDay; // 참가 신청 마지막 date

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MemberChallenge> memberChallenges = new ArrayList<>();

    public ChallengeDTO toDTO() {
        return ChallengeDTO.builder()
                .topic(topic)
                .howProof(howProof)
                .expectedResults(expectedResults)
                .pleaseNote(pleaseNote)
                .correctProof(correctProof)
                .periodStartDate(periodStartDate)
                .periodEndDate(periodEndDate)
                .expiredDay(expiredDay)
                .build();
    }

    public void update(ChallengeDTO challengeDTO) {
        this.topic = challengeDTO.getTopic();
        this.howProof = challengeDTO.getHowProof();
        this.expectedResults = challengeDTO.getExpectedResults();
        this.pleaseNote = challengeDTO.getPleaseNote();
        this.correctProof = challengeDTO.getCorrectProof();
        this.periodStartDate = challengeDTO.getPeriodStartDate();
        this.periodEndDate = challengeDTO.getPeriodEndDate();
        this.expiredDay = challengeDTO.getExpiredDay();
    }


}
