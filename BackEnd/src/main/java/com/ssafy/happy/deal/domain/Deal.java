package com.ssafy.happy.deal.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Deal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long dealAmount;

	@Column(nullable = false)
	private LocalDateTime dealDate;

	@Column(nullable = false)
	private Double area;

	@Column(nullable = false)
	private Integer floor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aptInfo_id")
	private AptInfo aptInfo;
}
