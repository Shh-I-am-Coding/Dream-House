package com.ssafy.happy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.happy.exception.UnauthorizedException;
import com.ssafy.happy.model.service.JwtService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor{
	private static final String HEADER_AUTH = "auth-token";

	private final JwtService jwtService;

	public JwtInterceptor(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		final String token = request.getHeader(HEADER_AUTH);

		if(token != null && jwtService.isUsable(token)){
			log.info("토큰 사용 가능 : {}", token);
			return true;
		}else{
			log.info("토큰 사용 불가능 : {}", token);
			throw new UnauthorizedException();
		}
	}
}
