package com.ssafy.happy.area.dto;

import com.ssafy.happy.area.domain.RoadName;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoadNameResponse {
	private long id;
	private String name;
	private int bonbun;
	private int bubun;

	public static RoadNameResponse of(RoadName roadName) {
		return RoadNameResponse.builder()
			.id(roadName.getId())
			.name(roadName.getName())
			.bonbun(roadName.getBonbun())
			.bubun(roadName.getBubun())
			.build();
	}
}
