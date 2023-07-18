package com.ssafy.happy.user.domain;

import com.ssafy.happy.user.dto.UserModifyRequest;
import javax.persistence.Entity;
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

    private String email;

    private String password;

    private String nickname;

    private String phone;

    private String authority;


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
