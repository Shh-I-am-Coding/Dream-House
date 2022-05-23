package com.ssafy.happy.model.service;

import com.ssafy.happy.dto.Deals;
import com.ssafy.happy.model.repo.DealsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealsServiceImpl implements DealsService{

    private final DealsRepo dealsRepo;

    public DealsServiceImpl(DealsRepo dealsRepo) {
        this.dealsRepo = dealsRepo;
    }


    @Override
    public List<Deals> getAllAptName(String dongCode, String sortBy, String sortOrder) {
        return dealsRepo.selectAptName(dongCode, sortBy, sortOrder);
    }
}
