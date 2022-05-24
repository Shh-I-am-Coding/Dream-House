package com.ssafy.happy.board.model.service;

import com.ssafy.happy.board.dto.Board;
import com.ssafy.happy.board.dto.BoardSearchCondition;

import java.util.Map;

public interface BoardService {
    boolean insert(Board board);
    boolean update(Board board);
    boolean delete(int articleNo);
    Board select(int articleNo);
    Map<String, Object> search(BoardSearchCondition condition);
    void increaseHit(int articleNo);
}
