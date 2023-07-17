package com.ssafy.happy.deal.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

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
	private Long id;

	@Column(length = 40)
	@NotNull
	private String name;

	@NotNull
	private Integer buildYear;

	@NotNull
	private Integer roadNameBonbunCode;

	@NotNull
	private Integer roadNameBubunCode;

	@NotNull
	private Integer bonbunCode;

	@NotNull
	private Integer bubunCode;

	@Column(length = 15)
	@NotNull
	private String jibun;

	@Column(length = 30)
	@NotNull
	private String lat;

	@Column(length = 30)
	@NotNull
	private String lng;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "road_name_code")
	@NotNull
	private RoadName roadName;

	@Builder.Default
	@OneToMany(mappedBy = "aptInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Deal> deals = new ArrayList<>();
}
