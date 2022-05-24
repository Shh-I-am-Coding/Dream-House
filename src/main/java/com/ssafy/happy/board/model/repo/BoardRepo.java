package com.ssafy.happy.board.model.repo;

import com.ssafy.happy.board.dto.Board;
import com.ssafy.happy.board.dto.BoardSearchCondition;

import java.util.List;

public interface BoardRepo {
    boolean insert(Board boardNo);
    boolean update(Board boardNo);
    boolean delete(int articleNo);
    Board select(int articleNo);
    List<Board> search(BoardSearchCondition condition);
    void increaseHit(int articleNo);
    int getTotalSearchCount(BoardSearchCondition condition);
}
