package com.ssafy.happy.model.service;

import java.util.List;

import com.ssafy.happy.dto.DealInfo;

public interface DealInfoService {
	List<DealInfo> getByDongName(String dongName, String sortBy, String sortOrder);
	List<DealInfo> getApt(String aptName, String sortBy, String sortOrder);
}
