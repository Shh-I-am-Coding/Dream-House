package com.ssafy.happy.user.dto;

import com.ssafy.happy.user.domain.User;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserModifyRequest {
    @NotBlank
    @Size(max = 40)
    private String password;

    @NotBlank
    @Size(max = 20)
    private String nickname;

    @NotBlank
    @Size(max = 30)
    private String phone;

    public User toEntity() {
        return User.builder()
                .password(this.password)
                .nickname(this.nickname)
                .phone(this.phone)
                .build();
    }
}
