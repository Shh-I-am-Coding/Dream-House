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

public class DealAvg {
	private int dealYear;
	private String avgAmount;
	private String pricePerArea;
}
