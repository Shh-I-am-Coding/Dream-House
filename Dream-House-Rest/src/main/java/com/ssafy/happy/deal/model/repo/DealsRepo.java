package com.ssafy.happy.deal.model.repo;

import java.util.List;

import com.ssafy.happy.deal.dto.Deals;

public interface DealsRepo {
	List<Deals> selectAptName(String dongCode, String sortBy, String sortOrder);

	List<Deals> topHitApts();
}
