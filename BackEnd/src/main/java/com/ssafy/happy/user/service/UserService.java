package com.ssafy.happy.user.service;

import com.ssafy.happy.user.domain.User;
import com.ssafy.happy.user.dto.UserJoinRequest;
import com.ssafy.happy.user.dto.UserLoginRequest;
import com.ssafy.happy.user.dto.UserLoginResponse;
import com.ssafy.happy.user.dto.UserModifyRequest;
import com.ssafy.happy.user.dto.UserResponse;
import com.ssafy.happy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;


	public void join(UserJoinRequest userJoinRequest) {
		userRepository.save(userJoinRequest.toEntity());
	}

	@Transactional(readOnly = true)
	public UserResponse findUser(Long id) {
		return UserResponse.of(userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")));
	}

	public void delete(Long id) {
		userRepository.delete(userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")));
	}

	@Transactional
	public void updateAccount(Long id, UserModifyRequest userModifyRequest) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
		user.update(userModifyRequest);
		userRepository.save(user);
	}

//	public String findPasswordByPhone(String id, String name, String phone) {
//		return userRepository.findPasswordByPhone(id, name, phone);
//	}
//
//	public String findPasswordByEmail(String id, String name, String email) {
//		return userRepository.findPasswordByEmail(id, name, email);
//	}

	@Transactional(readOnly = true)
	public void confirmPassword(Long id, String password) {
		boolean confirmed = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")).checkPassword(password);
		if(!confirmed) {
			throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
		}
	}

	public UserLoginResponse login(UserLoginRequest userLoginRequest) {
		User user = userRepository.findByEmail(userLoginRequest.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
		if(!user.checkPassword(userLoginRequest.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
		}
		return UserLoginResponse.of(user, jwtTokenProvider.createToken(String.valueOf(user.getId())));
	}

}