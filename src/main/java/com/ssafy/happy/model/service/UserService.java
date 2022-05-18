package com.ssafy.happy.model.service;

import java.sql.SQLException;

import com.ssafy.happy.dto.User;

public interface UserService {
	void register(User user) throws SQLException;

	User login(String id) throws SQLException;

	void deleteAccount(String id) throws SQLException;

	void updateAccount(User user) throws SQLException;

	String findPasswordByPhone(String id, String name, String phone);

	String findPasswordByEmail(String id, String name, String email);
}
