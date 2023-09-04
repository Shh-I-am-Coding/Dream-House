package com.ssafy.happy.deal.exception;

public class NotExistEmdCodeException extends RuntimeException {
	public NotExistEmdCodeException() {
		super("존재하지 않는 읍면동 코드 입니다.");
	}
}
