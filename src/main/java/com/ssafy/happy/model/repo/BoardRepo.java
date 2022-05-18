package com.ssafy.happy.model.repo;

import com.ssafy.happy.dto.Board;
import com.ssafy.happy.dto.SearchCondition;

import java.util.List;

public interface BoardRepo {
    boolean insert(Board board);
    boolean update(Board board);
    boolean delete(String boardNum);
    Board select(int boardNum);
    List<Board> search(SearchCondition condition);
}
