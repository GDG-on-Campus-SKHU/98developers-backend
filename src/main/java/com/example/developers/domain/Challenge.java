package com.example.developers.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
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
    private List<MemberChallenge> postHashtags = new ArrayList<>();

    public void setPostHashtags(List<MemberChallenge> postHashtags) {
        this.postHashtags = postHashtags;
    }

}
