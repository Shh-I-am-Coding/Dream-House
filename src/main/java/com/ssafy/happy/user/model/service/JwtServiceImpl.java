package com.ssafy.happy.user.model.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.happy.user.exception.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService{
	private static final int EXPIRE_MINUTES = 60;
	private static final String SALT = "happyHouseSecret";

	@Override
	public <T> String createToken(String key, T data, String subject) {
		String jwt = Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("regDate", System.currentTimeMillis())
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES)).setSubject(subject)
			.claim(key, data).signWith(SignatureAlgorithm.HS256, generateKey()).compact();
		return jwt;
	}

	private byte[] generateKey() {
		return SALT.getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
			.getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new UnauthorizedException();
		}
		Map<String, Object> value = claims.getBody();
		log.info("value : {}", value);
		return value;
	}

	@Override
	public boolean isUsable(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}
}
