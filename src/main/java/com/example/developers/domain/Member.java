package com.example.developers.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class Member implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(updatable = false, unique = true, nullable = false)
    private String userName;

    @Column
    private String email;
    @Column
    private String name;

    @Column
    private String picture;

    @Column(nullable = false)
    private String password;


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return null;
    }

    // 참고 https://zgundam.tistory.com/49

    // 계정 만료 여부, defalt true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김 여부, defalt true
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 패스워드 만료 여부, defalt true
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 여부, defalt true
    @Override
    public boolean isEnabled() {
        return true;
    }
}