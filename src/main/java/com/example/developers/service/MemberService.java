package com.example.developers.service;

import com.example.developers.DTO.JoinDTO;
import com.example.developers.DTO.TokenDTO;
import com.example.developers.jwt.TokenProvider;
import com.example.developers.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenDTO login(String memberId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDTO tokenDTO = tokenProvider.createToken(authentication);

        return tokenDTO;
    }

    @Transactional
    public void join(JoinDTO joinRequestDTO) {
        if(memberRepository.findByUserName(joinRequestDTO.getUserName()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        joinRequestDTO.setPassword(passwordEncoder.encode(joinRequestDTO.getPassword()));
        memberRepository.save(joinRequestDTO.toEntity());
    }

}
