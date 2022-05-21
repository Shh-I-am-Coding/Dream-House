package com.ssafy.happy.model.service;

import com.ssafy.happy.dto.User;
import com.ssafy.happy.model.repo.UserRepo;

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
	public int register(User user) {
		return userRepo.insert(user);
	}

	@Override
	public User login(String id) {
		return userRepo.select(id);
	}

	@Override
	@Transactional
	public int deleteAccount(String id) {
		return userRepo.delete(id);
	}

	@Override
	@Transactional
	public int updateAccount(User user) {
		return userRepo.update(user);
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
