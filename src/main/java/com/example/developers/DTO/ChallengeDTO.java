package com.example.developers.DTO;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDTO {

    private Long id;

    private String goal;

    private String authMethod;

    private String expectedEffects;

    private String caution;

    private Date date;

}
