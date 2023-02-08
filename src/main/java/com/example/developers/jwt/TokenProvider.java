package com.example.developers.jwt;

import com.example.developers.DTO.TokenDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    // JWT 시크릿 키를 받기 위한 Key 변수
    private final Key key;

    // 유효기간을 저장하기 위한 validityTime 변수
    private final long validityTime;

    public TokenProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.token-validity-in-milliseconds}") long validityTime) {
        // BASE64으로 디코딩
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        // String 형식 혹은 인코딩 된 byte 배열 형태의 비밀 키를 ScreteKey 객체로 변환
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.validityTime = validityTime;
    }

    // 토큰 생성 메서드
    public TokenDTO createToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        // 토큰 날짜 계산
        Date tokenExpiredTime = new Date(now + validityTime);

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName()) // payload "sub": "name" -> 유저 이름
                .claim("auth", authorities) // payload "auth": "ROLE_USER" -> 권한정보
                .setExpiration(tokenExpiredTime) // payload "exp": 1516239022 (예시) -> 만료 기간
                .signWith(key, SignatureAlgorithm.HS256) // header "alg": "HS256" -> hash : HS256
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(tokenExpiredTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenDTO.builder()
                .grantType("Bearer") //
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // Token에 담겨있는 정보를 이용해 Authentication 객체를 반환하는 메서드
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        // 권한이 없다면
        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // claims에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        // UsernamePasswordAuthenticationToken 형식으로 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰을 파싱하고 발생하는 예외를 처리, 문제가 있을 경우 false 반환
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            // 잘못된 JWT 서명일 경우
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            // 만료된 JWT 토큰일 경우
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            // 지원되지 않는 JWT 토큰일 경우
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            // JWT 토큰이 없을 경우
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    // 토큰 복호화
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
