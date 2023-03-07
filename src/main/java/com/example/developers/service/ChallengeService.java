package com.example.developers.service;

import com.example.developers.DTO.ChallengeDTO;
import com.example.developers.domain.Challenge;
import com.example.developers.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
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

    public void saveChallenge(ChallengeDTO challengeDTO) {

        Challenge challenge = Challenge.builder()
                .topic(challengeDTO.getTopic())
                .howProof(challengeDTO.getHowProof())
                .expectedResults(challengeDTO.getExpectedResults())
                .pleaseNote(challengeDTO.getPleaseNote())
                .collectProof(challengeDTO.getCollectProof())
                .periodStartDate(challengeDTO.getPeriodStartDate())
                .periodEndDate(challengeDTO.getPeriodEndDate())
                .expiredDay(challengeDTO.getExpiredDay())
                .build();
        challengeRepository.save(challenge);
    }

    public void updateChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = findByChallenge(challengeDTO.getId());
        challenge.update(challengeDTO);
        challengeRepository.save(challenge);
    }

    public Challenge findByChallenge(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 첼린지입니다."));
    }

}
