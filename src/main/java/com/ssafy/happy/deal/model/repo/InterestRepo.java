package com.ssafy.happy.deal.model.repo;

import java.util.List;

import com.ssafy.happy.deal.dto.Interest;
import com.ssafy.happy.user.dto.User;

public interface InterestRepo {
	List<Interest> selectAllInterests(String id);

	int insertInterest(Interest interest);
}
