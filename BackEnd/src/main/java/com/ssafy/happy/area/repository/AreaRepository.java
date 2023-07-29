package com.ssafy.happy.area.repository;

import com.ssafy.happy.area.domain.EmdArea;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<EmdArea, Long> {

}
