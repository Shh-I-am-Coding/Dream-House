package com.ssafy.happy.area.repository;

import com.ssafy.happy.area.domain.SidoArea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SidoAreaRepository extends JpaRepository<SidoArea, Long> {
    List<SidoArea> findall();

    SidoArea findSidoAreaById(Long code);
}
