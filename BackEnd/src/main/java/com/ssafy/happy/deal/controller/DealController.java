package com.ssafy.happy.deal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.common.dto.ApiResponse;
import com.ssafy.happy.deal.dto.AptInfoDetailResponse;
import com.ssafy.happy.deal.dto.AptInfoResponse;
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

	@GetMapping("/search")
	@ApiOperation(value = "동별 아파트 리스트 검색", notes = "동의 거래내역이 있는 모든 아파트 조회")
	public ResponseEntity<ApiResponse<List<AptInfoResponse>>> searchAptByEmdCode(@RequestParam String emdCode) {
		return ApiResponse.successWithData(dealService.searchAptByEmdCode(emdCode));
	}

	@GetMapping("/detail/{id}")
	@ApiOperation(value = "아파트 상세정보 조회", notes = "아파트 전체 정보 조회")
	public ResponseEntity<ApiResponse<AptInfoDetailResponse>> getAptInfoDetailById(@PathVariable Long id) {
		return ApiResponse.successWithData(dealService.getAptInfoDetail(id));
	}
}
