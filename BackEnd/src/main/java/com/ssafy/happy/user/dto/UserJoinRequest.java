package com.ssafy.happy.user.dto;

import com.ssafy.happy.user.constant.Authority;
import com.ssafy.happy.user.domain.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserJoinRequest {
	@Email
	@NotNull
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String nickname;

	@NotBlank
	private String phone;

	private Authority authority = Authority.MEMBER;

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
