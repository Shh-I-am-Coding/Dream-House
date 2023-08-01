package com.ssafy.happy.deal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.happy.deal.domain.AptInfo;
import com.ssafy.happy.deal.dto.AptInfoResponse;

public interface AptInfoRepository extends JpaRepository<AptInfo, Long> {

    @EntityGraph(attributePaths = {"deal"}, type = EntityGraphType.FETCH)
    @Query("select new com.ssafy.happy.deal.dto.AptInfoResponse(a.name, avg(d.dealAmount), a.lat, a.lng) "
        + "from AptInfo a left join a.deals d "
        + "where a.emdArea.code = :code "
        + "group by a.id")
    List<AptInfoResponse> getAptInfoByEmdArea(String code);
}
