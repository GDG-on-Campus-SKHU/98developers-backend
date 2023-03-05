package com.example.developers.controller;

import com.example.developers.DTO.ChallengeDTO;
import com.example.developers.DTO.ExploreDTO;
import com.example.developers.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;

    @GetMapping("/challenge")
    public ResponseEntity<List<ChallengeDTO>> findAllConnecting (
    ) {
        List<ChallengeDTO> challengeDTOS = challengeService.findAll();

        return ResponseEntity
                .ok(challengeDTOS);
    }
}
