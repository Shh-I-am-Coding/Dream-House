package com.ssafy.happy.deal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happy.deal.dto.AptInfoResponse;
import com.ssafy.happy.deal.repository.AptInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DealService {

	private AptInfoRepository aptInfoRepository;

	@Transactional(readOnly = true)
	public List<AptInfoResponse> searchAptByEmdCode(String code) {
		List<AptInfoResponse> aptInfoResponseList = aptInfoRepository.getAptInfoByEmdArea(code);
		// todo : Redis 캐싱 처리 추가
		return aptInfoResponseList;
	}
}