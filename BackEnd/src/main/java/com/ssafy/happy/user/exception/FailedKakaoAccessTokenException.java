package com.ssafy.happy.user.exception;

public class FailedKakaoAccessTokenException extends RuntimeException{
    public FailedKakaoAccessTokenException() {
        super("카카오 로그인에 실패하였습니다.");
    }
}
