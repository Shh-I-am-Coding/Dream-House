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
    public boolean insert(Board board) {
        return boardRepo.insert(board);
    }

    @Override
    public boolean update(Board board) {
        return boardRepo.update(board);
    }

    @Override
    public boolean delete(int boardNum) {
        return boardRepo.delete(boardNum);
    }

    @Override
    public Board select(int boardNum) {
        return boardRepo.select(boardNum);
    }

    @Override
    public List<Board> search(SearchCondition condition) {
        return boardRepo.search(condition);
    }

    @Override
    public void increaseHit(int boardNum) {
        boardRepo.increaseHit(boardNum);
    }
}
