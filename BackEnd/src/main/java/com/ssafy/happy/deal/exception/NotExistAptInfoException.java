package com.ssafy.happy.deal.exception;

public class NotExistAptInfoException extends RuntimeException {
	public NotExistAptInfoException() {
		super("존재하지 않는 아파트입니다.");
	}
}
