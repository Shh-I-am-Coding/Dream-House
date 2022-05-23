package com.ssafy.happy.model.repo;

import java.util.*;

import com.ssafy.happy.dto.DealInfo;

public interface DealInfoRepo {
	List<DealInfo> selectAllByDongCode(String dongCode, String sortBy, String sortOrder);

	List<DealInfo> selectApt(String aptName, String dongCode);
}
