package com.ssafy.happy.user.dto;

import com.ssafy.happy.user.domain.User;
import lombok.*;

@Getter
@Builder
public class UserJoinRequest {
	private String email;
	private String password;
	private String nickname;
	private String phone;
	private String authority;

	public User toEntity() {
		return User.builder()
				.email(this.email)
				.password(this.password)
				.nickname(this.nickname)
				.phone(this.phone)
				.authority(this.authority)
				.build();
	}
}
