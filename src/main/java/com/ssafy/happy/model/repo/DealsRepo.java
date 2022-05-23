package com.ssafy.happy.model.repo;

import com.ssafy.happy.dto.Deals;

import java.util.List;

public interface DealsRepo {
    List<Deals> selectAptName(String dongCode, String sortBy, String sortOrder);
}
