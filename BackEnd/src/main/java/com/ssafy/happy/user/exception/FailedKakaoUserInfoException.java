package com.ssafy.happy.user.exception;

import com.ssafy.happy.common.exception.InternalServerErrorException;

public class FailedKakaoUserInfoException extends InternalServerErrorException {
    public FailedKakaoUserInfoException() {
        super("카카오 로그인에 실패하였습니다.");
    }
}
