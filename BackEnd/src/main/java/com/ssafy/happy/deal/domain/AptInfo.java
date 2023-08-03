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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ssafy.happy.area.domain.EmdArea;

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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "road_name_code")
	private RoadName roadName;

	@Column(length = 40, nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer buildYear;

	@Column(nullable = false)
	private Integer roadNameBonbunCode;

	@Column(nullable = false)
	private Integer roadNameBubunCode;

	@Column(nullable = false)
	private Integer bonbunCode;

	@Column(nullable = false)
	private Integer bubunCode;

	@Column(length = 15, nullable = false)
	private String jibun;

	@Column(length = 30, nullable = false)
	private String lat;

	@Column(length = 30, nullable = false)
	private String lng;

	@Column(nullable = false)
	private Integer aptHit;

	@Builder.Default
	@OneToMany(mappedBy = "aptInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Deal> deals = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emd_area_id")
	private EmdArea emdArea;
}
