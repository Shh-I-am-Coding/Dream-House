package com.ssafy.happy.area.domain;

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

import com.ssafy.happy.deal.domain.AptInfo;

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
public class EmdArea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sigg_area_id")
	private SiggArea siggArea;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 10, nullable = false)
	private String code;

	@Builder.Default
	@OneToMany(mappedBy = "emdArea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AptInfo> aptInfoList = new ArrayList<>();
}
