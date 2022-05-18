package com.ssafy.happy.model.service;

import java.sql.SQLException;

import com.ssafy.happy.dto.User;
import com.ssafy.happy.model.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepo userRepo;

	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	@Transactional
	public void register(User user) throws SQLException {
		userRepo.insert(user);
	}

	@Override
	public User login(String id) throws SQLException {
		return userRepo.select(id);
	}

	@Override
	@Transactional
	public void deleteAccount(String id) throws SQLException {
		userRepo.delete(id);
	}
	
	@Override
	@Transactional
	public void updateAccount(User user) throws SQLException {
		userRepo.update(user);
	}

	@Override
	public String findPasswordByPhone(String id, String name, String phone) {
		return userRepo.findPasswordByPhone(id, name, phone);
	}

	@Override
	public String findPasswordByEmail(String id, String name, String email) {
		return userRepo.findPasswordByEmail(id, name, email);
	}

}
