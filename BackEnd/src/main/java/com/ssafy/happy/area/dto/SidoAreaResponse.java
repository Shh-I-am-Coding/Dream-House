package com.ssafy.happy.area.dto;

import com.ssafy.happy.area.domain.SidoArea;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SidoAreaResponse {
	private long id;
	private String name;
	private String code;

	public static SidoAreaResponse of(SidoArea sidoArea) {
		return SidoAreaResponse.builder()
			.id(sidoArea.getId())
			.name(sidoArea.getName())
			.code(sidoArea.getCode())
			.build();
	}
}
