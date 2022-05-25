package com.ssafy.happy.deal.model.repo;

import com.ssafy.happy.deal.dto.Deals;

import java.util.List;

public interface DealsRepo {
    List<Deals> selectAptName(String dongCode, String sortBy, String sortOrder);
    List<Deals> topHitApts();
}
