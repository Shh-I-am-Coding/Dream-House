package com.ssafy.happy.user.dto;

import com.ssafy.happy.user.domain.User;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserModifyRequest {
    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String phone;

    public User toEntity() {
        return User.builder()
                .password(this.password)
                .nickname(this.nickname)
                .phone(this.phone)
                .build();
    }
}
