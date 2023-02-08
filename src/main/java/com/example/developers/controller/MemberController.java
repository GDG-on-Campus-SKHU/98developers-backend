package com.example.developers.controller;

import com.example.developers.DTO.JoinDTO;
import com.example.developers.DTO.LoginDTO;
import com.example.developers.DTO.TokenDTO;
import com.example.developers.domain.Member;
import com.example.developers.repository.MemberRepository;
import com.example.developers.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

//    private final MemberRepository memberRepository;

    // /login 페이지 이동
    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginDTO memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getUserName();
        String password = memberLoginRequestDto.getPassword();
        TokenDTO tokenDTO = memberService.login(memberId, password);
        return tokenDTO;
    }

    @PostMapping("/join")
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