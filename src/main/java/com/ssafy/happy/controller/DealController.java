package com.ssafy.happy.controller;

import com.ssafy.happy.dto.DealInfo;
import com.ssafy.happy.model.service.DealInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin()
@RequestMapping("/deal")
public class DealController {

    private final DealInfoService dealInfoService;

    public DealController(DealInfoService dealInfoService) {
        this.dealInfoService = dealInfoService;
    }

    @GetMapping("/searchApt")
    public String searchApt() {
        return "/deal/searchApt";
    }

    @GetMapping("/getAptList")
    @ResponseBody
    public List<DealInfo> getAptList(String dong, String sortBy, String sortOrder){
        System.out.println(sortBy+" "+sortOrder);
        return dealInfoService.getByDongName(dong, sortBy, sortOrder);
    }

    @GetMapping("/getApt")
    @ResponseBody
    public List<DealInfo> getApt(String aptName, String sortBy, String sortOrder) {
        System.out.println(sortBy+" "+sortOrder);
        return dealInfoService.getApt(aptName, sortBy, sortOrder);
    }
}
