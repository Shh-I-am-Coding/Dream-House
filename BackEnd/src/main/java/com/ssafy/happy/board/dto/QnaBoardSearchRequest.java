package com.ssafy.happy.board.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaBoardSearchRequest {
    private String key = "none";
    private String word;
    private int pageNum = 1;
}
