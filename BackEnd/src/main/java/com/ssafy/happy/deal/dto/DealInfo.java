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
public class DealInfo {
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;
	private String aptCode;
	private String dealAmount;
	private String aptName;
	private String dongName;
	private String roadName;
	private int roadNameBonbun;
	private int roadNameBubun;
	private String jibun;
	private String dongCode;
	private String lng;
	private String lat;
}