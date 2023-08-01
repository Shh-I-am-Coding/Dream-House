package com.ssafy.happy.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginRequest {
    @NotBlank
    @Size(max = 30)
    private String email;

    @NotBlank
    @Size(max = 40)
    private String password;
}
