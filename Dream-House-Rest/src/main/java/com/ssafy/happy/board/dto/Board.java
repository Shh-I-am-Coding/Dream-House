package com.ssafy.happy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
