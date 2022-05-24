package com.ssafy.happy.board.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
    private int articleNo;
    private String userId;
    private String title;
    private String content;
    private int hit;
    private String regTime;
}
