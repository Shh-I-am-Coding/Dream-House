package com.ssafy.happy.notice.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.notice.dto.Notice;
import com.ssafy.happy.notice.model.service.NoticeService;
import com.ssafy.happy.notice.dto.NoticeSearchCondition;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	private final NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	private ResponseEntity<?> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/")
	public ResponseEntity<?> create(@RequestBody Notice article) {
		try {
			noticeService.insert(article);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/")
	public ResponseEntity<?> modify(@RequestBody Notice article) {
		try {
			noticeService.update(article);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/{articleNo}")
	public ResponseEntity<?> delete(@PathVariable int articleNo) {
		if (noticeService.delete(articleNo)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{articleNo}")
	public ResponseEntity<?> searchNotice(@PathVariable int articleNo) {
		System.out.println(articleNo);
		noticeService.increaseHit(articleNo);
		return new ResponseEntity<>(noticeService.select(articleNo), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<?> search(@ModelAttribute NoticeSearchCondition noticeSearchCondition) {
		System.out.println(noticeSearchCondition);
		try {
			Map<String, Object> notices = noticeService.search(noticeSearchCondition);
			if (notices != null && notices.size() > 0)
				return new ResponseEntity<>(notices, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

}
