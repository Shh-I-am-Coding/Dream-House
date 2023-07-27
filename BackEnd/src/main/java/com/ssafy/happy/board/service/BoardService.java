package com.ssafy.happy.board.service;

import com.ssafy.happy.board.domain.Board;
import com.ssafy.happy.board.dto.BoardModifyRequest;
import com.ssafy.happy.board.dto.BoardRequest;
import com.ssafy.happy.board.dto.BoardResponse;
import com.ssafy.happy.board.dto.BoardSearchRequest;
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
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        board.update(boardModifyRequest);
    }

    public void delete(Long id) {
        boardRepository.delete(boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다.")));
    }

    @Transactional(readOnly = true)
    public BoardResponse findOne(Long id) {
        return BoardResponse.of(boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다.")));
    }

    @Transactional(readOnly = true)
    public Page<BoardResponse> findAll(BoardSearchRequest boardSearchRequest) {
        String key = boardSearchRequest.getKey();
        Page<Board> boardPage;
        PageRequest pageRequest = PageRequest.of(boardSearchRequest.getPageNum(), 10, Direction.DESC, "createdDate");

        switch (key) {
            case "none" :
                boardPage = boardRepository.findAll(pageRequest);
                break;
            case "title":
                boardPage = boardRepository.findQnaBoardsByTitleContaining(
                        boardSearchRequest.getWord(), pageRequest);
                break;
            case "userId":
                boardPage = boardRepository.findQnaBoardsByUserId(
                        Long.parseLong(boardSearchRequest.getWord()), pageRequest);
                break;
            case "content":
                boardPage = boardRepository.findQnaBoardsByContentContaining(
                        boardSearchRequest.getWord(), pageRequest);
                break;
            default:
                throw new IllegalArgumentException("유효하지 않은 검색 키워드입니다.");
        }
        return boardPage.map(BoardResponse::of);
    }
}
