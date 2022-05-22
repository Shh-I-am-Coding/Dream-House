package com.ssafy.happy.model.service;

import java.util.List;

import com.ssafy.happy.dto.DealInfo;

public interface DealInfoService {
	List<DealInfo> getByDongCode(String dongCode, String sortBy, String sortOrder);
	List<DealInfo> getApt(String aptName, String dongCode, String sortBy, String sortOrder);
}
