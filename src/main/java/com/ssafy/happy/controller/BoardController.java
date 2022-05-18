package com.ssafy.happy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.dto.Board;
import com.ssafy.happy.dto.SearchCondition;
import com.ssafy.happy.model.service.BoardService;

@RestController
@CrossOrigin()
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	private ResponseEntity<?> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody Board board) {
		try {
			boardService.insert(board);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/")
	public ResponseEntity<?> modify(@RequestBody Board board) {
		try {
			boardService.update(board);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/{articleNo}")
	public ResponseEntity<?> delete(@PathVariable int articleNo) {
		if (boardService.delete(articleNo)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{articleNo}")
	public ResponseEntity<?> searchBoard(@PathVariable int articleNo) {
		System.out.println(articleNo);
		boardService.increaseHit(articleNo);
		return new ResponseEntity<>(boardService.select(articleNo), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> search(@RequestBody SearchCondition searchCondition) {
		System.out.println(searchCondition);
		try {
			Map<String, Object> boards = boardService.search(searchCondition);
			if (boards != null && boards.size() > 0)
				return new ResponseEntity<>(boards, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

}
