package com.ssafy.happy.board.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaBoardSearchRequest {
    private String key = "none";
    private String word;
    private int pageNum;
}
