package com.ssafy.happy.area.dto;

import com.ssafy.happy.area.domain.SiggArea;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SiggAreaResponse {
	private long id;
	private String name;
	private String code;

	public static SiggAreaResponse of(SiggArea siggArea) {
		return SiggAreaResponse.builder()
			.id(siggArea.getId())
			.name(siggArea.getName())
			.code(siggArea.getCode())
			.build();
	}
}
