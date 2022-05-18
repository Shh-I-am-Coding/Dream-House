package com.ssafy.happy.model.service;

import com.ssafy.happy.dto.Board;
import com.ssafy.happy.dto.SearchCondition;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    void insert(Board board);
    void delete(String boardNum);
    void update(Board board);
    Board select(int boardNum);
    List<Board> search(SearchCondition condition);
}
