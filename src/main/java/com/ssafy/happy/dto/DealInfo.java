package com.ssafy.happy.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DealInfo {
    private int idx;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private String area;
    private String floor;
    private int aptCode;
    private String dealAmount;
    private String aptName;
    private String dongName;
    private String jibun;
}