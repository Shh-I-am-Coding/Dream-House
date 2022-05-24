package com.ssafy.happy.deal.model.service;

import java.util.List;

import com.ssafy.happy.deal.dto.Interest;
import com.ssafy.happy.user.dto.User;

public interface InterestService {
	List<Interest> getInterests(String id);

	int registerInterests(Interest interest);
}
