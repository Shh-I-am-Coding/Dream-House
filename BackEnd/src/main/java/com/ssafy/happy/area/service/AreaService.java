package com.ssafy.happy.area.service;

import com.ssafy.happy.area.dto.SidoAreaResponse;
import com.ssafy.happy.area.repository.EmdAreaRepository;
import com.ssafy.happy.area.repository.SidoAreaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AreaService {

    private final SidoAreaRepository sidoAreaRepository;
    private final EmdAreaRepository emdAreaRepository;

    public List<SidoAreaResponse> getAllSidoArea() {
        return sidoAreaRepository.findAll().stream().map(SidoAreaResponse::of).collect(Collectors.toList());
    }
}
