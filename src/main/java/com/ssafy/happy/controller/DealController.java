package com.ssafy.happy.controller;

import java.util.List;

import com.ssafy.happy.dto.Deals;
import com.ssafy.happy.model.service.DealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.dto.DealInfo;
import com.ssafy.happy.model.service.DealInfoService;

@RestController
@RequestMapping("/deal")
public class DealController {

	private final DealInfoService dealInfoService;
	private final DealsService dealsService;

	public DealController(DealInfoService dealInfoService, DealsService dealsService) {
		this.dealInfoService = dealInfoService;
		this.dealsService = dealsService;
	}

	@GetMapping("/getAptList")
	public List<DealInfo> getAptList(String dong, String sortBy, String sortOrder) {
		System.out.println(sortBy + " " + sortOrder);
		return dealInfoService.getByDongCode(dong, sortBy, sortOrder);
	}

	@GetMapping("/getApt")
	public List<DealInfo> getApt(String aptName, String dongCode, String sortBy, String sortOrder) {
		System.out.println(sortBy + " " + sortOrder);
		return dealInfoService.getApt(aptName, dongCode, sortBy, sortOrder);
	}

	@GetMapping("/getAptNameList")
	public List<Deals> getAptNameList(String dongCode, String sortBy, String sortOrder) {
		System.out.println(dongCode+" "+sortBy + " " + sortOrder);
		return dealsService.getAllAptName(dongCode, sortBy, sortOrder);
	}
}
