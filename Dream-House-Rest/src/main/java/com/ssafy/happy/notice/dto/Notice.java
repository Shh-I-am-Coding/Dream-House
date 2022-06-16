package com.ssafy.happy.notice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notice {
    private int articleNo;
    private String userId;
    private String title;
    private String content;
    private int hit;
    private String regTime;
}
