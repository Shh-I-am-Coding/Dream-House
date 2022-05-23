package com.ssafy.happy.dto;

import lombok.*;

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