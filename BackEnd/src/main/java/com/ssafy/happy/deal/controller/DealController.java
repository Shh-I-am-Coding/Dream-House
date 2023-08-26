package com.ssafy.happy.deal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.common.dto.ApiResponse;
import com.ssafy.happy.deal.dto.AptInfoDetailResponse;
import com.ssafy.happy.deal.dto.AptInfoResponse;
import com.ssafy.happy.deal.dto.DealAverageResponse;
import com.ssafy.happy.deal.service.DealService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/deals")
public class DealController {
	private final DealService dealService;

	@GetMapping("/{emdCode}")
	@ApiOperation(value = "동별 아파트 리스트 검색", notes = "동의 거래내역이 있는 모든 아파트 조회")
	public ResponseEntity<ApiResponse<List<AptInfoResponse>>> searchAptByEmdCode(@PathVariable String emdCode) {
		return ApiResponse.successWithData(dealService.searchAptByEmdCode(emdCode));
	}

	@GetMapping("/detail/{id}")
	@ApiOperation(value = "아파트 상세정보 조회", notes = "특정 아파트의 전체 정보 조회")
	public ResponseEntity<ApiResponse<AptInfoDetailResponse>> getAptInfoDetailById(@PathVariable Long id) {
		return ApiResponse.successWithData(dealService.getAptInfoDetail(id));
	}

	@GetMapping("/average/{id}")
	@ApiOperation(value = "아파트 평균 조회", notes = "아파트 년도별 평균 및 평단가 평균 조회")
	public ResponseEntity<ApiResponse<List<DealAverageResponse>>> getDealAverageResponse(@PathVariable Long id) {
		return ApiResponse.successWithData(dealService.getDealAverageResponse(id));
	}

	@GetMapping("/tophits")
	@ApiOperation(value = "조회수 상위 아파트 조회", notes = "아파트 조회수 상위 4개 조회")
	public ResponseEntity<ApiResponse<List<AptInfoResponse>>> getTopHitAptInfo() {
		return ApiResponse.successWithData(dealService.getTopAptInfo());
	}
}
