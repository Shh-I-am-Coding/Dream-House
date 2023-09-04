package com.ssafy.happy.deal.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.happy.area.dto.EmdAreaResponse;
import com.ssafy.happy.area.dto.RoadNameResponse;
import com.ssafy.happy.deal.domain.AptInfo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AptInfoDetailResponse {
	private long id;
	private String aptCode;
	private RoadNameResponse roadName;
	private String name;
	private int buildYear;
	private int roadNameBonbunCode;
	private int roadNameBubunCode;
	private int bonbunCode;
	private int bubunCode;
	private String jibun;
	private String lat;
	private String lng;
	private int aptHit;
	private List<DealResponse> dealList;
	private EmdAreaResponse emdArea;

	public static AptInfoDetailResponse of(AptInfo aptInfo) {
		return AptInfoDetailResponse.builder()
			.id(aptInfo.getId())
			.aptCode(aptInfo.getAptCode())
			.roadName(RoadNameResponse.of(aptInfo.getRoadName()))
			.name(aptInfo.getName())
			.buildYear(aptInfo.getBuildYear())
			.roadNameBonbunCode(aptInfo.getRoadNameBonbunCode())
			.roadNameBubunCode(aptInfo.getRoadNameBubunCode())
			.bonbunCode(aptInfo.getBonbunCode())
			.bubunCode(aptInfo.getBubunCode())
			.jibun(aptInfo.getJibun())
			.lat(aptInfo.getLat())
			.lng(aptInfo.getLng())
			.aptHit(aptInfo.getAptHit())
			.dealList(aptInfo.getDealList().stream().map(DealResponse::of).collect(Collectors.toList()))
			.emdArea(EmdAreaResponse.of(aptInfo.getEmdArea()))
			.build();
	}
}
