package com.ssafy.happy.area.service;

import com.ssafy.happy.area.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AreaService {

    private final AreaRepository areaRepository;
}
