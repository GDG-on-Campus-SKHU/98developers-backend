package com.example.developers.service;

import com.example.developers.DTO.ChallengeDTO;
import com.example.developers.domain.Challenge;
import com.example.developers.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public List<ChallengeDTO> findAll() {
        List<Challenge> challenges = challengeRepository.findAll();
        return challenges.stream()
                .map(Challenge::toDTO)
                .collect(Collectors.toList());
    }


}
