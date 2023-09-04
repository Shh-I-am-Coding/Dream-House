package com.ssafy.happy.area.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happy.area.repository.EmdAreaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AreaService {

    private final EmdAreaRepository areaRepository;
}
