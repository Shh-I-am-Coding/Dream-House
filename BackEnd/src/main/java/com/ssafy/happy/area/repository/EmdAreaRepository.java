package com.ssafy.happy.area.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.happy.area.domain.EmdArea;

public interface EmdAreaRepository extends JpaRepository<EmdArea, Long> {
	boolean existsEmdAreaByCode(String code);
}
