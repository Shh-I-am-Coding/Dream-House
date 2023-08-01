package com.ssafy.happy.user.exception;

public class FailedKakaoUserInfoException extends RuntimeException{
    public FailedKakaoUserInfoException() {
        super("카카오 로그인에 실패하였습니다.");
    }
}
