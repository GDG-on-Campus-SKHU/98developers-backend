package com.example.developers.service;

import com.example.developers.DTO.JoinDTO;
import com.example.developers.DTO.TokenDTO;
import com.example.developers.domain.Member;
import com.example.developers.jwt.TokenProvider;
import com.example.developers.repository.MemberRepository;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional(readOnly = true)
    public Member findByUsername(String username) {
        Member member;
        try {
            member = loadUserByUsername(username);
        }
        catch (UsernameNotFoundException e)
        {
            return null;
        }
        return member;
    }

    @Override
    @Transactional(readOnly = true)
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUid(username)
                .orElseThrow(() -> new NoSuchElementException(String.format("해당 유저(%s)를 찾을 수 없습니다.", username)));
    }

    @Transactional
    public Member save(FirebaseToken firebaseToken) {
        log.info("FirebaseTOken ---- "+ firebaseToken.getEmail()+ " "+ firebaseToken.getUid());
        Member member = Member.builder()
                .uid(firebaseToken.getUid())
                .email(firebaseToken.getEmail())
                .name(firebaseToken.getName())
                .avatar(firebaseToken.getPicture())
                .roles(Collections.singletonList("USER"))
                .password("")
                .build();
        memberRepository.save(member);
        return member;
    }

}
