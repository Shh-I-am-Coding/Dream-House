package com.ssafy.happy.board.dto;

import com.ssafy.happy.board.domain.QnaBoard;
import com.ssafy.happy.user.dto.UserResponse;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QnaBoardResponse {
    private Long id;
    private UserResponse user;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;

    public static QnaBoardResponse of(QnaBoard qnaBoard) {
        return QnaBoardResponse.builder()
                .id(qnaBoard.getId())
                .user(UserResponse.of(qnaBoard.getUser()))
                .title(qnaBoard.getTitle())
                .content(qnaBoard.getContent())
                .hit(qnaBoard.getHit())
                .createdDate(qnaBoard.getCreatedDate())
                .build();
    }
}
