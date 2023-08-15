package com.ssafy.happy.deal.dto;

import java.time.LocalDateTime;

import com.ssafy.happy.deal.domain.Deal;

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
    private long id;
    private long dealAmount;
    private LocalDateTime dealDate;
    private double area;
    private int floor;

    public static DealResponse of(Deal deal) {
        return DealResponse.builder()
            .id(deal.getId())
            .dealAmount(deal.getDealAmount())
            .dealDate(deal.getDealDate())
            .area(deal.getArea())
            .floor(deal.getFloor())
            .build();
    }
}
