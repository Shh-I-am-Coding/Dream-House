package com.ssafy.happy.model.repo;

import java.sql.SQLException;

import com.ssafy.happy.dto.User;

public interface UserRepo {
	void insert(User user) throws SQLException;

	User select(String id) throws SQLException;

	void delete(String id) throws SQLException;

	void update(User user) throws SQLException;

	String findPasswordByPhone(String id, String name, String phone);

	String findPasswordByEmail(String id, String name, String email);
}
