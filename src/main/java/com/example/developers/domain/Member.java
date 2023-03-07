package com.example.developers.domain;

import com.example.developers.DTO.MemberDTO;
import com.google.firebase.auth.FirebaseToken;
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

    @Column(name = "uid", updatable = false, unique = true, nullable = false)
    private String uid;

    @Column
    private String email;
    @Column
    private String name;

    @Column
    private String avatar;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MemberChallenge> memberChallenges = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public void update(FirebaseToken token) {
        this.uid = token.getUid();
        this.email = token.getEmail();
        this.name = token.getName();
        this.avatar = token.getPicture();
    }

    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .uid(uid)
                .email(email)
                .name(name)
                .avatar(avatar)
                .build();
    }

    @Override
    public String getUsername() {
        return uid;
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