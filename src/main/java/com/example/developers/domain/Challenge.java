package com.example.developers.domain;

import com.example.developers.DTO.ChallengeDTO;
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
    private String goal;

    @Column(name = "auth_method")
    private String authMethod;

    @Column(name = "expected_effects")
    private String expectedEffects;

    @Column
    private String caution;

    @Column
    private Date date;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MemberChallenge> memberChallenges = new ArrayList<>();

    public ChallengeDTO toDTO() {
        return ChallengeDTO.builder()
                .id(id)
                .authMethod(authMethod)
                .caution(caution)
                .date(date)
                .expectedEffects(expectedEffects)
                .goal(goal)
                .build();
    }

}
