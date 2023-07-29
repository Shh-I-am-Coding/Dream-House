package com.ssafy.happy.deal.repository;

import com.ssafy.happy.deal.domain.AptInfo;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AptInfoRepository extends JpaRepository<AptInfo, Long> {

    @EntityGraph(attributePaths = {"deal"}, type = EntityGraphType.FETCH)
    @Query("select a from AptInfo a left join a.deals where a.emdArea.code = :code")
    List<AptInfo> getAptInfoByEmdArea(String code);
}
