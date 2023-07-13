package com.ssafy.happy.common.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.happy.user.exception.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
	private static final long VALID_TIME = 60 * 60 * 1000L;
	private static final String SALT = "dreamHouseSecret";

	public String createToken(String payload) {
		Claims claims = Jwts.claims().setSubject(payload);
		Date now = new Date();

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + VALID_TIME))
				.signWith(SignatureAlgorithm.HS256, generateKey())
				.compact();
	}

	private byte[] generateKey() {
		return SALT.getBytes(StandardCharsets.UTF_8);
	}

	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
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
