package com.example.developers.service;

import com.example.developers.DTO.ChallengeDTO;
import com.example.developers.DTO.JoinDTO;
import com.example.developers.DTO.MemberDTO;
import com.example.developers.DTO.TokenDTO;
import com.example.developers.domain.Member;
import com.example.developers.domain.MemberChallenge;
import com.example.developers.jwt.TokenProvider;
import com.example.developers.repository.MemberChallengeRepository;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final MemberChallengeRepository memberChallengeRepository;

    public MemberDTO findByMemberIdInChallenge(Member user) {
        MemberDTO memberDTO = loadUserByUsername(user.getUid()).toDTO();
        List<MemberChallenge> memberChallenge = memberChallengeRepository.findMemberChallengeByMemberId(1);
        List<ChallengeDTO> addChallenge = new ArrayList<>();
        for(MemberChallenge m : memberChallenge)
            addChallenge.add(m.getChallenge().toDTO());
        memberDTO.setChallenges(addChallenge);
        return memberDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUid(username)
                .orElseThrow(() -> new NoSuchElementException(String.format("해당 유저(%s)를 찾을 수 없습니다.", username)));
    }
    @Transactional
    public Member updateByUsername(FirebaseToken firebaseToken) {
        Member user = loadUserByUsername(firebaseToken.getUid());

        user.update(firebaseToken);

        return memberRepository.save(user);
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
