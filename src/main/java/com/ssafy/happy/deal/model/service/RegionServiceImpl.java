package com.ssafy.happy.deal.model.service;

import java.util.List;

import com.ssafy.happy.deal.dto.Region;
import com.ssafy.happy.user.dto.User;
import com.ssafy.happy.deal.model.repo.RegionRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegionServiceImpl implements RegionService {
	private final RegionRepo regionRepo;

	public RegionServiceImpl(RegionRepo dao) {
		this.regionRepo = dao;
	}

	@Override
	public List<Region> getFavoriteRegions(User user) {
		return regionRepo.selectAllFavoriteRegions(user);
	}

	@Override
	@Transactional
	public void registerFavoriteRegions(String id, Region region) {
		regionRepo.insertFavoriteRegions(id, region);
	}
}
