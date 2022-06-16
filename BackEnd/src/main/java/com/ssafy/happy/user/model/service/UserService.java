package com.ssafy.happy.user.model.service;

import com.ssafy.happy.user.dto.User;

public interface UserService {
	int register(User user);

	User search(String id);

	int deleteAccount(String id);

	int updateAccount(User user);

	String findPasswordByPhone(String id, String name, String phone);

	String findPasswordByEmail(String id, String name, String email);

	String getKakaoAccessToken(String code);

	User createKakaoUser(String token);
}
