package com.ssafy.happy.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(SQLException.class)
	public String handleSqlException(SQLException e, Model m) {
		log.error("sql exception: ", e.getCause());
		m.addAttribute("errmsg", "SQL 에러 발생!!!");
		return "error/sqlError";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException e, Model m) {
		log.error("404 발생 : {}", "404 page not found");
		m.addAttribute("errmsg", "요청하신 파일은 존재하지 않습니다.");
		return "error/404";
	}
}
