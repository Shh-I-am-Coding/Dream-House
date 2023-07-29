package com.ssafy.happy.board.exception;

public class NotExistBoardException extends RuntimeException {
    public NotExistBoardException() {
        super("존재하지 않는 게시물입니다.");
    }
}
