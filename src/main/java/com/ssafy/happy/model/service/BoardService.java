package com.ssafy.happy.model.service;

import com.ssafy.happy.dto.Board;
import com.ssafy.happy.dto.SearchCondition;

import java.util.Map;

public interface BoardService {
    boolean insert(Board board);
    boolean update(Board board);
    boolean delete(int articleNo);
    Board select(int articleNo);
    Map<String, Object> search(SearchCondition condition);
    void increaseHit(int articleNo);
}
