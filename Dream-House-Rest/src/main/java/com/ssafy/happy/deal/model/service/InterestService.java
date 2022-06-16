package com.ssafy.happy.deal.model.service;

import java.util.List;

import com.ssafy.happy.deal.dto.Deals;
import com.ssafy.happy.deal.dto.Interest;

public interface InterestService {
	List<Deals> getInterests(String id);

	int registerInterests(Interest interest);

	int deleteInterests(Interest interest);

}
