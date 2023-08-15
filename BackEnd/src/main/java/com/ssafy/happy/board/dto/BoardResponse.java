package com.ssafy.happy.board.dto;

import com.ssafy.happy.board.domain.Board;
import com.ssafy.happy.user.constant.Authority;
import com.ssafy.happy.user.domain.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;
    private Long userId;
    private String userEmail;
    private String userNickname;
    private Authority userAuthority;

    public static BoardResponse of(Board board) {
        User user = board.getUser();
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .hit(board.getHit())
                .createdDate(board.getCreatedDate())
                .userId(user.getId())
                .userEmail(user.getEmail())
                .userNickname(user.getNickname())
                .userAuthority(user.getAuthority())
                .build();
    }
}
