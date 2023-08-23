package com.ssafy.happy.deal.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DealAverageResponse {
	private int dealYear;
	private double dealAverage;
	private double pricePerArea;
}
