package com.ssafy.happy.user.exception;

import com.ssafy.happy.common.exception.InternalServerErrorException;

public class NotExistUserException extends InternalServerErrorException {
    public NotExistUserException() {
        super("존재하지 않는 회원입니다.");
    }
}
