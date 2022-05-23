package com.ssafy.happy.model.service;

import java.util.List;

import com.ssafy.happy.dto.Deals;

public interface DealsService {
    List<Deals> getAllAptName(String dongCode, String sortBy, String sortOrder);
}
