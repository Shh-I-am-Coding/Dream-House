package com.ssafy.happy.deal.model.repo;

import java.util.List;

import com.ssafy.happy.deal.dto.DealInfo;

public interface DealInfoRepo {
	List<DealInfo> selectAllByDongCode(String dongCode, String sortBy, String sortOrder);

	List<DealInfo> selectApt(String aptName, String dongCode);
}
