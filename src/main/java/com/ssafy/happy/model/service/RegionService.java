package com.ssafy.happy.model.service;

import java.util.List;

import com.ssafy.happy.dto.Region;
import com.ssafy.happy.dto.User;

public interface RegionService {
	List<Region> getFavoriteRegions(User user);

	void registerFavoriteRegions(String id, Region region);
}
