package com.ssafy.happy.board.constant;

import com.ssafy.happy.board.domain.Board;
import com.ssafy.happy.board.dto.BoardSearchRequest;
import com.ssafy.happy.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public enum SearchKey {
    NONE {
        @Override
        public Page<Board> search(BoardRepository boardRepository, BoardSearchRequest boardSearchRequest, PageRequest pageRequest) {
            return boardRepository.findAllByCategory(boardSearchRequest.getCategory(), pageRequest);
        }
    }, TITLE {
        @Override
        public Page<Board> search(BoardRepository boardRepository, BoardSearchRequest boardSearchRequest, PageRequest pageRequest) {
            return boardRepository.findBoardsByCategoryAndContentContaining(boardSearchRequest.getCategory(),
                    boardSearchRequest.getWord(), pageRequest);
        }
    }, USERID {
        @Override
        public Page<Board> search(BoardRepository boardRepository, BoardSearchRequest boardSearchRequest, PageRequest pageRequest) {
            return boardRepository.findBoardsByCategoryAndUserId(boardSearchRequest.getCategory(),
                    Long.parseLong(boardSearchRequest.getWord()), pageRequest);
        }
    }, CONTENT {
        @Override
        public Page<Board> search(BoardRepository boardRepository, BoardSearchRequest boardSearchRequest,
                           PageRequest pageRequest) {
            return boardRepository.findBoardsByCategoryAndTitleContaining(boardSearchRequest.getCategory(),
                    boardSearchRequest.getWord(), pageRequest);
        }
    };

    public abstract Page<Board> search(BoardRepository boardRepository, BoardSearchRequest boardSearchRequest, PageRequest pageRequest);
}
