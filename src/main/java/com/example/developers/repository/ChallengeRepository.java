package com.example.developers.repository;

import com.example.developers.domain.Challenge;
import com.example.developers.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
