package com.example.developers.DTO;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMemberChallengeDTO {
    String uid;

    boolean success;
}
