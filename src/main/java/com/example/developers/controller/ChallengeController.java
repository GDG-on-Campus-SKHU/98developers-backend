package com.example.developers.controller;

import com.example.developers.DTO.ChallengeDTO;
import com.example.developers.DTO.ExploreDTO;
import com.example.developers.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;

    @GetMapping("/challenges")
    public ResponseEntity<List<ChallengeDTO>> findAllChallenges (
    ) {
        List<ChallengeDTO> challengeDTOS = challengeService.findAll();

        return ResponseEntity
                .ok(challengeDTOS);
    }
    @PostMapping("/api/challenge/save")
    public ResponseEntity<String> saveChallenge(
            @RequestBody ChallengeDTO challengeDTO
    ) {
        challengeService.saveChallenge(challengeDTO);
        return ResponseEntity.ok("save challenge");
    }

    @PatchMapping("/api/challenge/update")
    ResponseEntity<String> updateChallenge(
            @RequestBody ChallengeDTO challengeDTO
    ) {
        challengeService.updateChallenge(challengeDTO);
        return ResponseEntity.ok("update challenge");
    }
}
