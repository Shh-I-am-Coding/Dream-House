package com.ssafy.happy.user.constant;

import com.ssafy.happy.user.domain.RefreshToken;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token {
    private String accessToken;
    private RefreshToken refreshToken;
}
