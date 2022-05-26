package com.ssafy.happy.board.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.happy.board.dto.Board;
import com.ssafy.happy.board.dto.BoardSearchCondition;
import com.ssafy.happy.board.model.repo.BoardRepo;
import com.ssafy.happy.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService {
	private final BoardRepo boardRepo;

	public BoardServiceImpl(BoardRepo boardRepo) {
		this.boardRepo = boardRepo;
	}

	@Override
	public boolean insert(Board board) {
		return boardRepo.insert(board);
	}

	@Override
	public boolean update(Board board) {
		return boardRepo.update(board);
	}

	@Override
	public boolean delete(int articleNo) {
		return boardRepo.delete(articleNo);
	}

	@Override
	public Board select(int articleNo) {
		return boardRepo.select(articleNo);
	}

	@Override
	public Map<String, Object> search(BoardSearchCondition condition) {
		int totalCount = boardRepo.getTotalSearchCount(condition);
		PageNavigation pageNavigation = new PageNavigation(condition.getCurrentPage(), totalCount);
		Map<String, Object> map = new HashMap<>();
		map.put("articles", boardRepo.search(condition));
		map.put("pageNavigation", pageNavigation);
		return map;
	}

	@Override
	public void increaseHit(int articleNo) {
		boardRepo.increaseHit(articleNo);
	}
}
