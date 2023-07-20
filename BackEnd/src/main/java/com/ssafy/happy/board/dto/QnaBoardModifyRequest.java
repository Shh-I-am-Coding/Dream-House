package com.ssafy.happy.board.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaBoardModifyRequest {
    private String title;
    private String content;
    private int hit;
}
