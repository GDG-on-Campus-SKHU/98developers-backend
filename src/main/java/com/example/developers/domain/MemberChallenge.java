package com.example.developers.domain;

import com.example.developers.DTO.ChallengeMemberDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user_challenge")
public class MemberChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @Column(nullable = true)
    private String image;

    @Column
    @ColumnDefault("false")
    private Boolean success;

    public void imageUpdate(String imageSrc) {
        this.image = imageSrc;
    }

    public void changeIsSuccess(boolean success){
        this.success = success;
    }

    public ChallengeMemberDTO challengeToDTO() {
        return ChallengeMemberDTO.builder()
                .member(member.toDTO())
                .image(image)
                .success(success)
                .build();
    }


}