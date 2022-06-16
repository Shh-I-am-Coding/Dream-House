package com.ssafy.happy.news.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.news.domain.NaverCrawler;

@RestController
@RequestMapping("/news")
public class NewsController {

	private final NaverCrawler crawler;

	public NewsController(NaverCrawler crawler) {
		this.crawler = crawler;
	}

	@GetMapping("/")
	public ResponseEntity<List<Map<String, Object>>> getNews() {
		return new ResponseEntity<>(crawler.getNews(), HttpStatus.OK);
	}
}
