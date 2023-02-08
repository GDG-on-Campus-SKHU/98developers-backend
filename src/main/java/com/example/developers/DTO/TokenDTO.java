package com.example.developers.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenDTO {
    private String grantType; // 권한의 위임 방법을 제공
    private String accessToken; // 요청에 대한 다양한 정보를 담고 실질적 인증 역할
    private String refreshToken; // Access Token의 만료 기간을 조정하는 역할
}
