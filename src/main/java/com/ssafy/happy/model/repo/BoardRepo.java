package com.ssafy.happy.model.repo;

import com.ssafy.happy.dto.Board;
import com.ssafy.happy.dto.SearchCondition;

import java.util.List;

public interface BoardRepo {
    void insert(Board board);
    void delete(String boardNum);
    void update(Board board);
    Board select(int boardNum);
    List<Board> search(SearchCondition condition);
}
