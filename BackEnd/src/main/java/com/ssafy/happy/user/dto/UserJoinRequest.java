package com.ssafy.happy.user.dto;

import com.ssafy.happy.user.constant.Authority;
import com.ssafy.happy.user.domain.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserJoinRequest {
	@Email
	@NotNull
	@Size(max = 50)
	private String email;

	@NotBlank
	@Size(max = 40)
	private String password;

	@NotBlank
	@Size(max = 20)
	private String nickname;

	@NotBlank
	@Size(max = 30)
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
