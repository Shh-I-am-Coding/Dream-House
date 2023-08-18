package com.ssafy.happy.deal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happy.deal.domain.AptInfo;
import com.ssafy.happy.deal.dto.AptInfoDetailResponse;
import com.ssafy.happy.deal.dto.AptInfoResponse;
import com.ssafy.happy.deal.exception.NotExistAptInfoException;
import com.ssafy.happy.deal.repository.AptInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DealService {

	private final AptInfoRepository aptInfoRepository;

	@Transactional(readOnly = true)
	public List<AptInfoResponse> searchAptByEmdCode(String code) {
		List<AptInfoResponse> aptInfoResponseList = aptInfoRepository.getAptInfoByEmdArea(code);
		// todo : Redis 캐싱 처리 추가
		return aptInfoResponseList;
	}

	@Transactional(readOnly = true)
	public AptInfoDetailResponse getAptInfoDetail(Long id) {
		AptInfo aptInfo = aptInfoRepository.getAptInfoById(id)
			.orElseThrow(NotExistAptInfoException::new);
		return AptInfoDetailResponse.of(aptInfo);
	}
}
