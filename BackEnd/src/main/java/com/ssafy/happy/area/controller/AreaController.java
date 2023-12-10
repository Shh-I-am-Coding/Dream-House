package com.ssafy.happy.area.controller;

import com.ssafy.happy.area.dto.SidoAreaResponse;
import com.ssafy.happy.area.dto.SiggAreaResponse;
import com.ssafy.happy.area.service.AreaService;
import com.ssafy.happy.common.dto.ApiResponse;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<ApiResponse<List<SidoAreaResponse>>> getAllSidoArea() {
        return ApiResponse.successWithData(areaService.getAllSidoArea());
    }

    @GetMapping("/sigg")
    @ApiOperation(value = "시군구 조회", notes = "시군구 코드, 이름 조회")
    public ResponseEntity<ApiResponse<List<SiggAreaResponse>>> getSiggArea(@PathVariable Long code) {
        return ApiResponse.successWithData(areaService.getSiggArea(code));
    }
}
