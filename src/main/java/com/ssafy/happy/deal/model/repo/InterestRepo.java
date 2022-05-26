package com.ssafy.happy.deal.model.repo;

import java.util.List;

import com.ssafy.happy.deal.dto.Deals;
import com.ssafy.happy.deal.dto.Interest;

public interface InterestRepo {
	List<Deals> selectAllInterests(String id);

	int insertInterest(Interest interest);

	int deleteInterests(Interest interest);

}
