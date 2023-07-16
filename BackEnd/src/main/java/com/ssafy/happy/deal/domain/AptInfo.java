package com.ssafy.happy.deal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AptInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	@Column(length = 40)
	private String name;

	private Integer buildYear;

	private Integer roadNameBonbunCode;

	private Integer roadNameBubunCode;

	private Integer bonbunCode;

	private Integer bubunCode;

	@Column(length = 15)
	private String jibun;

	@Column(length = 30)
	private String lat;

	@Column(length = 30)
	private String lng;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "road_name_code")
	private RoadName roadName;
}
