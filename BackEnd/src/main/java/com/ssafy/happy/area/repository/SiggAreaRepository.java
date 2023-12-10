package com.ssafy.happy.area.repository;

import com.ssafy.happy.area.domain.SidoArea;
import com.ssafy.happy.area.domain.SiggArea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiggAreaRepository extends JpaRepository<SiggArea, Long> {
    List<SiggArea> findAllBySidoArea(SidoArea sidoArea);
}
