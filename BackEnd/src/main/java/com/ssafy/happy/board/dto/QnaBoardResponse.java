package com.ssafy.happy.board.dto;

import com.ssafy.happy.board.domain.QnaBoard;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QnaBoardResponse {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;

    public static QnaBoardResponse of(QnaBoard qnaBoard) {
        return QnaBoardResponse.builder()
                .id(qnaBoard.getId())
                .userId(qnaBoard.getUserId())
                .title(qnaBoard.getTitle())
                .content(qnaBoard.getContent())
                .hit(qnaBoard.getHit())
                .createdDate(qnaBoard.getCreatedDate())
                .build();
    }
}
