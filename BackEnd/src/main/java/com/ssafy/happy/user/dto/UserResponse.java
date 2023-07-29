package com.ssafy.happy.user.dto;

import com.ssafy.happy.user.constant.Authority;
import com.ssafy.happy.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String phone;
    private Authority authority;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .authority(user.getAuthority())
                .build();
    }
}
