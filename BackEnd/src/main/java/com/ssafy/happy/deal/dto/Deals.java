package com.ssafy.happy.deal.dto;

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
public class Deals {
	private int dealNum;
	private String aptCode;
	private String aptName;
	private String dongName;
	private String dongCode;
	private String lng;
	private String lat;
	private String avgAmount;
}