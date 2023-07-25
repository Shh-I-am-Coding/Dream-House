package com.ssafy.happy.user.dto;

import com.ssafy.happy.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserModifyRequest {
    private String password;
    private String nickname;
    private String phone;

    public User toEntity() {
        return User.builder()
                .password(this.password)
                .nickname(this.nickname)
                .phone(this.phone)
                .build();
    }
}
