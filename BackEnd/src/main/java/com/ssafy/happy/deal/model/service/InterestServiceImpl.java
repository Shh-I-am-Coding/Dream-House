package com.ssafy.happy.deal.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happy.deal.dto.Deals;
import com.ssafy.happy.deal.dto.Interest;
import com.ssafy.happy.deal.model.repo.InterestRepo;

@Service
public class InterestServiceImpl implements InterestService {
	private final InterestRepo interestRepo;

	public InterestServiceImpl(InterestRepo dao) {
		this.interestRepo = dao;
	}

	@Override
	public List<Deals> getInterests(String id) {
		return interestRepo.selectAllInterests(id);
	}

	@Override
	@Transactional
	public int registerInterests(Interest interest) {
		return interestRepo.insertInterest(interest);
	}

	@Override
	@Transactional
	public int deleteInterests(Interest interest) {
		return interestRepo.deleteInterests(interest);
	}
}
