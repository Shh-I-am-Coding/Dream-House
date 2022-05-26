package com.ssafy.happy.deal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.deal.dto.Deals;
import com.ssafy.happy.deal.dto.Interest;
import com.ssafy.happy.deal.model.service.InterestService;

@RestController
@RequestMapping("/interest")
public class InterestController {
	private InterestService interestService;

	public InterestController(InterestService interestService) {
		this.interestService = interestService;
	}

	@GetMapping("/")
	public List<Deals> getInterests(String id) {
		System.out.println(id);
		return interestService.getInterests(id);
	}

	@PutMapping("/")
	public ResponseEntity<String> registerInterests(@RequestBody Interest interest) {
		System.out.println("register" + interest);
		if (interestService.registerInterests(interest) > 0) {
			return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/")
	public ResponseEntity<String> deleteInterests(Interest interest) {
		System.out.println("delete" + interest);
		if (interestService.deleteInterests(interest) > 0) {
			return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}