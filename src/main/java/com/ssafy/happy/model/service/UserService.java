package com.ssafy.happy.model.service;

import com.ssafy.happy.dto.User;

public interface UserService {
	int register(User user);

	User login(String id);

	int deleteAccount(String id);

	int updateAccount(User user);

	String findPasswordByPhone(String id, String name, String phone);

	String findPasswordByEmail(String id, String name, String email);
}
