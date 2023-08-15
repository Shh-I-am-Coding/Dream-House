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
        + "from AptInfo a left join a.dealList d "
        + "where a.emdArea.code = :code "
        + "group by a.id")
    List<AptInfoResponse> getAptInfoByEmdArea(String code);

    @EntityGraph(attributePaths = {"deal, roadName, emdArea"}, type = EntityGraphType.FETCH)
    @Query("select a "
        + "from AptInfo a left join a.dealList d "
        + "where a.id = :id ")
    AptInfo getAptInfoById(Long id);
}
