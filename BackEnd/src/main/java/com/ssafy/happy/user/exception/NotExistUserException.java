package com.ssafy.happy.user.exception;

public class NotExistUserException extends RuntimeException {
    public NotExistUserException() {
        super("존재하지 않는 회원입니다.");
    }
}
