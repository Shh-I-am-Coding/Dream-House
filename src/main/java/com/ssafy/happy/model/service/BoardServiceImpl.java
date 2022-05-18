package com.ssafy.happy.model.service;

import com.ssafy.happy.dto.Board;
import com.ssafy.happy.dto.SearchCondition;
import com.ssafy.happy.model.repo.BoardRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardRepo boardRepo;

    public BoardServiceImpl(BoardRepo boardRepo) {
        this.boardRepo = boardRepo;
    }

    @Override
    public void insert(Board board) {

    }

    @Override
    public void delete(String boardNum) {

    }

    @Override
    public void update(Board board) {

    }

    @Override
    public Board select(int boardNum) {
        return null;
    }

    @Override
    public List<Board> search(SearchCondition condition) {
        return null;
    }
}
