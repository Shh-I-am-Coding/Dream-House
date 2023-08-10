package com.ssafy.happy.user.domain;

import com.ssafy.happy.user.constant.Authority;
import com.ssafy.happy.user.dto.UserModifyRequest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 40, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 30, nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Authority authority;


    public void update(UserModifyRequest userModifyRequest) {
        this.password = userModifyRequest.getPassword();
        this.password = userModifyRequest.getPassword();
        this.nickname = userModifyRequest.getNickname();
        this.phone = userModifyRequest.getPhone();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
