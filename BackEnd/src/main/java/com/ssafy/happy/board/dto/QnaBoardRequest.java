package com.ssafy.happy.board.dto;

import com.ssafy.happy.board.domain.QnaBoard;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class QnaBoardRequest {
	private Long userId;
	private String title;
	private String content;

	public QnaBoard toEntity() {
		return QnaBoard.builder()
				.userId(userId)
				.title(title)
				.content(content)
				.build();
	}
}
