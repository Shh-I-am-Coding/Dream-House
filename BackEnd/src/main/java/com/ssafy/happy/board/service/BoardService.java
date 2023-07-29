package com.ssafy.happy.board.service;

import com.ssafy.happy.board.domain.Board;
import com.ssafy.happy.board.dto.BoardModifyRequest;
import com.ssafy.happy.board.dto.BoardRequest;
import com.ssafy.happy.board.dto.BoardResponse;
import com.ssafy.happy.board.dto.BoardSearchRequest;
import com.ssafy.happy.board.exception.NotExistBoardException;
import com.ssafy.happy.board.repository.BoardRepository;
import com.ssafy.happy.user.domain.User;
import com.ssafy.happy.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    public void create(BoardRequest boardRequest) {
        User user = userRepository.findById(boardRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        boardRepository.save(boardRequest.toEntity(user));
    }

    public void update(Long id, BoardModifyRequest boardModifyRequest) {
        Board board = boardRepository.findById(id).orElseThrow(NotExistBoardException::new);
        board.update(boardModifyRequest);
    }

    public void delete(Long id) {
        boardRepository.delete(boardRepository.findById(id)
                .orElseThrow(NotExistBoardException::new));
    }

    @Transactional(readOnly = true)
    public BoardResponse findOne(Long id) {
        return BoardResponse.of(
                boardRepository.findById(id).orElseThrow(NotExistBoardException::new));
    }

    @Transactional(readOnly = true)
    public Page<BoardResponse> findAll(BoardSearchRequest boardSearchRequest) {
        PageRequest pageRequest = PageRequest.of(boardSearchRequest.getPageNum(), 10, Direction.DESC, "createdDate");
        Page<Board> boardPage = boardSearchRequest.getSearchKey().search(boardRepository, boardSearchRequest, pageRequest);
        return boardPage.map(BoardResponse::of);
    }
}
