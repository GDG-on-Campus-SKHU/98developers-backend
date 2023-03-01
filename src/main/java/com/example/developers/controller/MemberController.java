package com.example.developers.controller;

import com.example.developers.DTO.JoinDTO;
import com.example.developers.DTO.LoginDTO;
import com.example.developers.DTO.MemberDTO;
import com.example.developers.DTO.TokenDTO;
import com.example.developers.util.RequestUtil;


import com.example.developers.domain.Member;
import com.example.developers.service.ExploreService;
import com.example.developers.service.MemberService;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    private final ExploreService exploreService;

    @GetMapping("/crawling/sparrowClub")
    public ResponseEntity<String> testing() throws Exception {
        exploreService.crawlingSparrowClub();
        return ResponseEntity.ok("crawling success");
    }

    private final FirebaseAuth firebaseAuth;

    @PostMapping("")
    public MemberDTO register(@RequestHeader("Authorization") String authorization) {
        // TOKEN을 가져온다.
        log.info("POSTTTTTTTTTTTTTTTTTTTTTTTTTT");
        FirebaseToken decodedToken;
        try {
            String token = RequestUtil.getAuthorizationToken(authorization);
            decodedToken = firebaseAuth.verifyIdToken(token);
        } catch (IllegalArgumentException | FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }
        // 사용자를 등록한다.
        Member registeredUser = memberService.findByUsername(decodedToken.getUid());
        log.info("++++++ " + registeredUser);

        return new MemberDTO(registeredUser);
    }

    @GetMapping("/me")
    public MemberDTO getUserMe(Authentication authentication) {
        log.info("GETTTTTTTTTTTTTTTTTTTT");
        Member member = ((Member) authentication.getPrincipal());
        log.info("{}"+member);
        return new MemberDTO(member);
    }




    // /login 페이지 이동
    @PostMapping("/signin")
    public TokenDTO login(@RequestBody LoginDTO memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getUserName();
        String password = memberLoginRequestDto.getPassword();
        TokenDTO tokenDTO = memberService.login(memberId, password);
        return tokenDTO;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> join(@RequestBody JoinDTO joinRequestDto) {
        memberService.join(joinRequestDto);
        return ResponseEntity.ok("join success");
    }



    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("admin");
    }

    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("user");
    }

    @GetMapping("/index")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("index");
    }

}