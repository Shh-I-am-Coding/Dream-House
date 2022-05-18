package com.ssafy.happy.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
    private int boardNum;
    private String writer;
    private String title;
    private String text;
    private int hit;
}
