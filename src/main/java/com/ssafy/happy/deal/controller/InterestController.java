package com.ssafy.happy.deal.controller;

import com.ssafy.happy.deal.dto.Interest;
import com.ssafy.happy.deal.model.service.InterestService;
import com.ssafy.happy.user.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interest")
public class InterestController {
    private InterestService interestService;

    public InterestController(InterestService interestService){
        this.interestService = interestService;
    }

    @GetMapping("/")
    public List<Interest> getInterests(String id) {
        System.out.println(id);
        return interestService.getInterests(id);
    }

    @PutMapping("/")
    public ResponseEntity<String> registerInterests(@RequestBody Interest interest) {
        System.out.println(interest);
        if(interestService.registerInterests(interest) > 0) {
            return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}