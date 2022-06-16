package com.ssafy.happy.user.model.repo;

import com.ssafy.happy.user.dto.User;

public interface UserRepo {
	int insert(User user);

	User select(String id);

	int delete(String id);

	int update(User user);

	String findPasswordByPhone(String id, String name, String phone);

	String findPasswordByEmail(String id, String name, String email);
}
