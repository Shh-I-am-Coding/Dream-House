package com.ssafy.happy.deal.model.service;

import java.util.List;

import com.ssafy.happy.deal.dto.Deals;

public interface DealsService {
    List<Deals> getAllAptName(String dongCode, String sortBy, String sortOrder);
}
