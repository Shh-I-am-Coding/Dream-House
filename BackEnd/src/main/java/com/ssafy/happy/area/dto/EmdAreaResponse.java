package com.ssafy.happy.area.dto;

import com.ssafy.happy.area.domain.EmdArea;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmdAreaResponse {
	private long id;
	private String name;
	private String code;

	public static EmdAreaResponse of(EmdArea emdArea) {
		return EmdAreaResponse.builder()
			.id(emdArea.getId())
			.name(emdArea.getName())
			.code(emdArea.getCode())
			.build();
	}
}
