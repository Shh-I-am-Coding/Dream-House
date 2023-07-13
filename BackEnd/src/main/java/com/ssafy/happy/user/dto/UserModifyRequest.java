package com.ssafy.happy.user.dto;

import com.ssafy.happy.user.domain.User;
import lombok.Getter;

@Getter
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
