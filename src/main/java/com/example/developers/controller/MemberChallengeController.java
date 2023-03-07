package com.example.developers.controller;

import com.example.developers.DTO.ChallengeDTO;
import com.example.developers.domain.Member;
import com.example.developers.service.ChallengeService;
import com.example.developers.service.MemberChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberChallengeController {
    private final MemberChallengeService memberChallengeService;

    @PostMapping("/user/{challengeId}/takePhoto")
    public ResponseEntity<String> saveImgUser(
            Authentication authentication,
            @PathVariable Long challengeId,
            MultipartFile image
    ) throws IOException {
        Member member = ((Member) authentication.getPrincipal());
        memberChallengeService.updateMemberChallengeInfo(member ,image, challengeId);

        return ResponseEntity.ok("save user img");
    }
}
