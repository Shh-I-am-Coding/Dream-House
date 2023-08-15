package com.ssafy.happy.board.service;

import com.ssafy.happy.board.domain.Board;
import com.ssafy.happy.board.dto.BoardModifyRequest;
import com.ssafy.happy.board.dto.BoardRequest;
import com.ssafy.happy.board.dto.BoardResponse;
import com.ssafy.happy.board.dto.BoardSearchRequest;
import com.ssafy.happy.board.exception.IsNotBoardAuthorExcepiton;
import com.ssafy.happy.board.exception.NotExistBoardException;
import com.ssafy.happy.board.repository.BoardRepository;
import com.ssafy.happy.user.dto.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void create(UserAccount account, BoardRequest boardRequest) {
        boardRepository.save(boardRequest.toEntity(account.getUser()));
    }

    public void update(Long id, UserAccount account, BoardModifyRequest boardModifyRequest) {
        Board board = boardRepository.findById(id).orElseThrow(NotExistBoardException::new);
        if(!board.getUser().getId().equals(account.getUser().getId())) {
            throw new IsNotBoardAuthorExcepiton();
        }
        board.update(boardModifyRequest);
    }

    public void delete(Long id, UserAccount account) {
        Board board = boardRepository.findById(id).orElseThrow(NotExistBoardException::new);
        if(!board.getUser().getId().equals(account.getUser().getId())) {
            throw new IsNotBoardAuthorExcepiton();
        }
        boardRepository.delete(board);
    }

    @Transactional(readOnly = true)
    public BoardResponse findOne(Long id) {
        return BoardResponse.of(boardRepository.findById(id).orElseThrow(NotExistBoardException::new));
    }

    @Transactional(readOnly = true)
    public Page<BoardResponse> findAll(BoardSearchRequest boardSearchRequest) {
        PageRequest pageRequest = PageRequest.of(boardSearchRequest.getPageNum(), 10, Direction.DESC, "createdDate");
        Page<Board> boardPage = boardSearchRequest.getSearchKey().search(boardRepository, boardSearchRequest, pageRequest);
        return boardPage.map(BoardResponse::of);
    }
}
