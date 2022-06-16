package com.ssafy.happy.board.model.repo;

import java.util.List;

import com.ssafy.happy.board.dto.Board;
import com.ssafy.happy.board.dto.BoardSearchCondition;

public interface BoardRepo {
	boolean insert(Board boardNo);

	boolean update(Board boardNo);

	boolean delete(int articleNo);

	Board select(int articleNo);

	List<Board> search(BoardSearchCondition condition);

	void increaseHit(int articleNo);

	int getTotalSearchCount(BoardSearchCondition condition);
}
