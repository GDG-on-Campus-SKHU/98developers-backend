package com.example.developers.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = resolveToken((HttpServletRequest) request);

        if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorzation");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }

        return null;
    }
}