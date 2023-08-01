package com.ssafy.happy.user.exception;

public class FailedSendEmailException extends RuntimeException{
    public FailedSendEmailException() {
        super("이메일을 보내는 데 실패했습니다.");
    }
}
