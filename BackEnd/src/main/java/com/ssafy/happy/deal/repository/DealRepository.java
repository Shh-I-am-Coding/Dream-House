package com.ssafy.happy.deal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.happy.deal.domain.AptInfo;
import com.ssafy.happy.deal.dto.AptInfoResponse;
import com.ssafy.happy.deal.dto.DealAverageResponse;

public interface DealRepository extends JpaRepository<AptInfo, Long> {

    // todo: EntityGraph 자세히 알아보기

    //  @EntityGraph(attributePaths = {"dealList"}, type = EntityGraphType.FETCH)
    @Query("select new com.ssafy.happy.deal.dto.AptInfoResponse(a.name, avg(d.dealAmount), a.lat, a.lng) "
        + "from AptInfo a left join a.dealList d "
        + "where a.emdArea.code = :code "
        + "group by a.id")
    List<AptInfoResponse> getAptInfoByEmdArea(String code);

    @EntityGraph(attributePaths = {"dealList, roadName, emdArea"}, type = EntityGraphType.FETCH)
    @Query("select a "
        + "from AptInfo a left join a.dealList d "
        + "where a.id = :id ")
    Optional<AptInfo> getAptInfoById(Long id);

    @Query(
        "SELECT new com.ssafy.happy.deal.dto.DealAverageResponse(year(d.dealDate), avg(d.dealAmount), avg(d.dealAmount / d.area)) "
            + "FROM AptInfo a left join a.dealList d "
            + "WHERE a.id = :id "
            + "GROUP BY year(d.dealDate)")
    List<DealAverageResponse> getDealAverageById(Long id);

    @Query("SELECT new com.ssafy.happy.deal.dto.AptInfoResponse() "
        + "FROM AptInfo a LEFT JOIN a.dealList d "
        + "ORDER BY a.aptHit DESC")
    List<AptInfoResponse> getTop4AptInfo();
}