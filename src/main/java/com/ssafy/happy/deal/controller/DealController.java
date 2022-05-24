package com.ssafy.happy.deal.controller;

import java.util.List;

import com.ssafy.happy.deal.dto.Deals;
import com.ssafy.happy.deal.model.service.DealsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.deal.dto.DealInfo;
import com.ssafy.happy.deal.model.service.DealInfoService;

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
	public List<DealInfo> getAptList(String dongCode, String sortBy, String sortOrder) {
		System.out.println(dongCode+" "+sortBy + " " + sortOrder);
		return dealInfoService.getByDongCode(dongCode, sortBy, sortOrder);
	}

	@GetMapping("/getApt")
	public List<DealInfo> getApt(String aptName, String dongCode) {
		System.out.println(aptName+" "+dongCode);
		return dealInfoService.getApt(aptName, dongCode);
	}

	@GetMapping("/getAptNameList")
	public List<Deals> getAptNameList(String dongCode, String sortBy, String sortOrder) {
		System.out.println(dongCode+" "+sortBy + " " + sortOrder);
		return dealsService.getAllAptName(dongCode, sortBy, sortOrder);
	}
}
