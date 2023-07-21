package com.ssafy.happy.board.dto;

import com.ssafy.happy.board.domain.QnaBoard;
import com.ssafy.happy.user.domain.User;
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
	private Long userId;
	private String title;
	private String content;

	public QnaBoard toEntity(User user) {
		return QnaBoard.builder()
				.user(user)
				.title(title)
				.content(content)
				.build();
	}
}
