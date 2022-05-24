package com.ssafy.happy.deal.model.service;

import java.util.List;

import com.ssafy.happy.deal.dto.Region;
import com.ssafy.happy.user.dto.User;

public interface RegionService {
	List<Region> getFavoriteRegions(User user);

	void registerFavoriteRegions(String id, Region region);
}
