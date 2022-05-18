package com.ssafy.happy.model.service;

import java.util.List;

import com.ssafy.happy.model.repo.DealInfoRepo;
import com.ssafy.happy.dto.DealInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealInfoServiceImpl implements DealInfoService {
	private final DealInfoRepo dealInfoRepo;

	public DealInfoServiceImpl(DealInfoRepo dealInfoRepo) {
		this.dealInfoRepo = dealInfoRepo;
	}

	@Override
	public List<DealInfo> getByDongName(String dongName, String sortBy, String sortOrder) {
		return dealInfoRepo.selectAllByDongName(dongName, sortBy, sortOrder);
	}

	@Override
	public List<DealInfo> getApt(String aptName, String sortBy, String sortOrder) {
		return dealInfoRepo.selectApt(aptName, sortBy, sortOrder);
	}

}
