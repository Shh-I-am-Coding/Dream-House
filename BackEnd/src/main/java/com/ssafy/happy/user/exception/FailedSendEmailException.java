package com.ssafy.happy.user.exception;

import com.ssafy.happy.common.exception.BadRequestException;

public class FailedSendEmailException extends BadRequestException {
    public FailedSendEmailException() {
        super("이메일을 보내는 데 실패했습니다.");
    }
}
