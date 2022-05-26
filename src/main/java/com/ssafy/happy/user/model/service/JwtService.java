package com.ssafy.happy.user.model.service;

import java.util.Map;

public interface JwtService {
	<T> String createToken(String key, T data, String subject);

	Map<String, Object> get(String key);

	boolean isUsable(String jwt);
}
