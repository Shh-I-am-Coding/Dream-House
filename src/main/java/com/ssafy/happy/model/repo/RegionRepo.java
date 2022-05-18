package com.ssafy.happy.model.repo;

import java.util.List;

import com.ssafy.happy.dto.Region;
import com.ssafy.happy.dto.User;

public interface RegionRepo {
	List<Region> selectAllFavoriteRegions(User user);

	void insertFavoriteRegions(String id, Region region);
}
