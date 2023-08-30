package com.ssafy.happy.deal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happy.area.repository.EmdAreaRepository;
import com.ssafy.happy.deal.domain.AptInfo;
import com.ssafy.happy.deal.dto.AptInfoDetailResponse;
import com.ssafy.happy.deal.dto.AptInfoResponse;
import com.ssafy.happy.deal.dto.DealAverageResponse;
import com.ssafy.happy.deal.exception.NotExistAptInfoException;
import com.ssafy.happy.deal.exception.NotExistEmdCodeException;
import com.ssafy.happy.deal.repository.DealRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DealService {

	private final DealRepository aptInfoRepository;
	private final EmdAreaRepository areaRepository;

	@Transactional(readOnly = true)
	public List<AptInfoResponse> searchAptByEmdCode(String code) {
		if (areaRepository.existsEmdAreaByCode(code)) {
			throw new NotExistEmdCodeException();
		}
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

	@Transactional(readOnly = true)
	public List<DealAverageResponse> getDealAverageResponse(Long id) {
		List<DealAverageResponse> dealAverageResponseList = aptInfoRepository.getDealAverageById(id);
		if (dealAverageResponseList.isEmpty()) {
			throw new NotExistAptInfoException();
		}
		return dealAverageResponseList;
	}

	@Transactional(readOnly = true)
	public List<AptInfoResponse> getTopAptInfo() {
		List<AptInfoResponse> aptInfoResponseList = aptInfoRepository.getTop4AptInfo();
		// todo: Redis 캐싱처리 추가
		return aptInfoResponseList;
	}
}
