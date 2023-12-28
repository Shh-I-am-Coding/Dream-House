package com.ssafy.happy.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60)
@AllArgsConstructor
@Getter
public class RefreshToken {
    @Id
    private String refreshToken;
    private String email;

    public boolean isSameUser(String email) {
        return this.email.equals(email);
    }
}