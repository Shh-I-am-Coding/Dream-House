package com.ssafy.happy.user.exception;

import com.ssafy.happy.common.exception.BadRequestException;

public class WrongPasswordException extends BadRequestException {
    public WrongPasswordException() {
        super("비밀번호가 틀렸습니다.");
    }
}
