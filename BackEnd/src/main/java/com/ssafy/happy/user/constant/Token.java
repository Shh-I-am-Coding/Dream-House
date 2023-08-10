package com.ssafy.happy.user.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token {
    private String accessToken;
    private String refreshToken;
}
