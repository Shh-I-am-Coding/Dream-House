package com.ssafy.happy.board.dto;

import com.ssafy.happy.board.domain.QnaBoard;
import com.ssafy.happy.user.domain.User;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class QnaBoardRequest {
	@NotNull
	private Long userId;

	@NotBlank
	@Size(min = 1, max = 100)
	private String title;

	@NotBlank
	private String content;

	public QnaBoard toEntity(User user) {
		return QnaBoard.builder()
				.user(user)
				.title(title)
				.content(content)
				.build();
	}
}
