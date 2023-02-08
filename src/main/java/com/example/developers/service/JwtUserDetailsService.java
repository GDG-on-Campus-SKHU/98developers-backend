package com.example.developers.service;

import com.example.developers.domain.Member;
import com.example.developers.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // USer 찾아서 UserDeails 객체 형식으로 return
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUserName(username)
                .map(this::createUserDetails)
                .orElseThrow(()->new UsernameNotFoundException("해당 ID를 찾을 수 없습니다."));
    }

    // UserDetails 생성
    private UserDetails createUserDetails(Member member){
        return User.builder()
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRoles().toArray(new String[0]))
                .build();
    }
}