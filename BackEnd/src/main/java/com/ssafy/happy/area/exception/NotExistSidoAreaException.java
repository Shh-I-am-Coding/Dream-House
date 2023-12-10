package com.ssafy.happy.area.exception;

public class NotExistSidoAreaException extends RuntimeException {
    public NotExistSidoAreaException() {
        super("존재하지 않는 시도 코드입니다.");
    }
}
