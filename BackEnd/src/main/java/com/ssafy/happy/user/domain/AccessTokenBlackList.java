package com.ssafy.happy.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "accessToken")
@AllArgsConstructor
@Getter
public class AccessTokenBlackList {
    @Id
    private String accessToken;
}
