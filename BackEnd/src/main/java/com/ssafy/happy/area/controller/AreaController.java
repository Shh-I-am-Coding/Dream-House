package com.ssafy.happy.area.controller;

import com.ssafy.happy.area.dto.SidoAreaResponse;
import com.ssafy.happy.area.service.AreaService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/areas")
public class AreaController {

    private final AreaService areaService;

    @GetMapping("/sido")
    @ApiOperation(value = "전체 시도 조회", notes = "대한민국 전체 시도 이름, 코드 조회")
    public List<SidoAreaResponse> getAllSidoArea() {
        return areaService.getAllSidoArea();
    }
}
