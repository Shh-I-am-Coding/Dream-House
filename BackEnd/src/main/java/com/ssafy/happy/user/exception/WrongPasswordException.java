package com.ssafy.happy.user.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("비밀번호가 틀렸습니다.");
    }
}
