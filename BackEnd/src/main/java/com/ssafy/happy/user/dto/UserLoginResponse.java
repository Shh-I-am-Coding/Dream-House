package com.ssafy.happy.user.dto;

import com.ssafy.happy.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginResponse {
    private final Long id;
    private final String email;
    private final String password;
    private final String nickname;
    private final String phone;
    private final String authority;
    private final String accessToken;

    public static UserLoginResponse of(User user, String accessToken) {
        return UserLoginResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .authority(user.getAuthority())
                .accessToken(accessToken)
                .build();
    }
}
