package com.ssafy.happy.board.dto;

import com.ssafy.happy.board.domain.Board;
import com.ssafy.happy.user.dto.UserResponse;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponse {
    private Long id;
    private UserResponse user;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;

    public static BoardResponse of(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .user(UserResponse.of(board.getUser()))
                .title(board.getTitle())
                .content(board.getContent())
                .hit(board.getHit())
                .createdDate(board.getCreatedDate())
                .build();
    }
}
