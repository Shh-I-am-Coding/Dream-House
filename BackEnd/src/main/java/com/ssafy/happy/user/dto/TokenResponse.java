package com.ssafy.happy.user.dto;

import com.ssafy.happy.user.domain.RefreshToken;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private RefreshToken refreshToken;
}
