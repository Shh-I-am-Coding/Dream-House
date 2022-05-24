package com.ssafy.happy.deal.model.repo;

import java.util.List;

import com.ssafy.happy.deal.dto.Region;
import com.ssafy.happy.user.dto.User;

public interface RegionRepo {
	List<Region> selectAllFavoriteRegions(User user);

	void insertFavoriteRegions(String id, Region region);
}
