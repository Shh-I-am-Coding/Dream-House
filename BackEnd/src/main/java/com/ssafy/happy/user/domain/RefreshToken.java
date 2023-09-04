package com.ssafy.happy.user.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60)
@AllArgsConstructor
public class RefreshToken {
    @Id
    private String refreshToken;
    private String email;
}
