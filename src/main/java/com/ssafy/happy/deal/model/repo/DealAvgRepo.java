package com.ssafy.happy.deal.model.repo;

import com.ssafy.happy.deal.dto.DealAvg;

import java.util.List;

public interface DealAvgRepo {
    List<DealAvg> getDealAvg(String aptCode);
    void increaseHit(String aptName, String dongCode);
}
