package com.ssafy.happy.board.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaBoardSearchRequest {
    private String key;
    private String word;
    private int pageNum;
}
