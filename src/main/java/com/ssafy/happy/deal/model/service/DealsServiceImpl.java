package com.ssafy.happy.deal.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happy.deal.dto.Deals;
import com.ssafy.happy.deal.model.repo.DealsRepo;

@Service
public class DealsServiceImpl implements DealsService {

	private final DealsRepo dealsRepo;

	public DealsServiceImpl(DealsRepo dealsRepo) {
		this.dealsRepo = dealsRepo;
	}

	@Override
	public List<Deals> getAllAptName(String dongCode, String sortBy, String sortOrder) {
		return dealsRepo.selectAptName(dongCode, sortBy, sortOrder);
	}

	@Override
	public List<Deals> topHitApts() {
		return dealsRepo.topHitApts();
	}
}
