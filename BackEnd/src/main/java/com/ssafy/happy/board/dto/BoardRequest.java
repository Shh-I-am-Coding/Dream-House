package com.ssafy.happy.board.dto;

import com.ssafy.happy.board.domain.Board;
import com.ssafy.happy.board.domain.Category;
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
public class BoardRequest {
	@NotNull
	private Long userId;

	@NotNull
	private Category category;

	@NotBlank
	@Size(min = 1, max = 100)
	private String title;

	@NotBlank
	private String content;

	public Board toEntity(User user) {
		return Board.builder()
				.user(user)
				.category(category)
				.title(title)
				.content(content)
				.build();
	}
}
