package com.ssafy.happy.deal.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DealResponse {
    private long dealAmount;
    private LocalDateTime dealDate;
    private double area;
    private int floor;
}
