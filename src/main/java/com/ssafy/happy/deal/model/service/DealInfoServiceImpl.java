package com.ssafy.happy.deal.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happy.deal.dto.DealAvg;
import com.ssafy.happy.deal.dto.DealInfo;
import com.ssafy.happy.deal.model.repo.DealAvgRepo;
import com.ssafy.happy.deal.model.repo.DealInfoRepo;

@Service
public class DealInfoServiceImpl implements DealInfoService {
	private final DealInfoRepo dealInfoRepo;
	private final DealAvgRepo dealAvgRepo;

	public DealInfoServiceImpl(DealInfoRepo dealInfoRepo, DealAvgRepo dealAvgRepo) {
		this.dealInfoRepo = dealInfoRepo;
		this.dealAvgRepo = dealAvgRepo;
	}

	@Override
	public List<DealInfo> getByDongCode(String dongCode, String sortBy, String sortOrder) {
		return dealInfoRepo.selectAllByDongCode(dongCode, sortBy, sortOrder);
	}

	@Override
	public List<DealInfo> getApt(String aptName, String dongCode) {
		dealAvgRepo.increaseHit(aptName, dongCode);
		return dealInfoRepo.selectApt(aptName, dongCode);
	}

	@Override
	public List<DealAvg> getDealAvg(String aptCode) {
		return dealAvgRepo.getDealAvg(aptCode);
	}

}
