package com.ssafy.happy.user.service;

import com.google.gson.JsonParser;
import com.ssafy.happy.common.util.EmailSender;
import com.ssafy.happy.common.util.JwtTokenProvider;
import com.ssafy.happy.user.domain.User;
import com.ssafy.happy.user.dto.UserAccount;
import com.ssafy.happy.user.dto.UserJoinRequest;
import com.ssafy.happy.user.dto.UserLoginRequest;
import com.ssafy.happy.user.dto.UserLoginResponse;
import com.ssafy.happy.user.dto.UserModifyRequest;
import com.ssafy.happy.user.dto.UserResponse;
import com.ssafy.happy.user.exception.FailedSendEmailException;
import com.ssafy.happy.user.exception.NotExistUserException;
import com.ssafy.happy.user.exception.WrongPasswordException;
import com.ssafy.happy.user.repository.UserRepository;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final EmailSender emailSender;

	public void join(UserJoinRequest userJoinRequest) {
		userRepository.save(userJoinRequest.toEntity());
	}

	@Transactional(readOnly = true)
	public UserResponse findUser(Long id) {
		return UserResponse.of(userRepository.findById(id)
				.orElseThrow(NotExistUserException::new));
	}

	@Transactional(readOnly = true)
	public UserLoginResponse login(UserLoginRequest userLoginRequest) {
		User user = userRepository.findByEmail(userLoginRequest.getEmail())
				.orElseThrow(NotExistUserException::new);
		if(!user.checkPassword(userLoginRequest.getPassword())) {
			throw new WrongPasswordException();
		}
		return UserLoginResponse.of(user, jwtTokenProvider.createTokens(user.getEmail()));
	}

	public void update(UserAccount account, UserModifyRequest userModifyRequest) {
		account.getUser().update(userModifyRequest);
	}

	public void delete(UserAccount account) {
		userRepository.delete(account.getUser());
	}

	public String sendEmail(String email) {
		email = JsonParser.parseString(email).getAsJsonObject().get("email").getAsString().trim();

		String certifiedCode;
		try {
			certifiedCode = emailSender.sendMail(email);
		} catch (MessagingException e) {
			throw new FailedSendEmailException();
		}
		return certifiedCode;
	}

	@Transactional(readOnly = true)
	public void confirmPassword(UserAccount account, String password) {
		if(!account.getUser().checkPassword(password)) {
			throw new WrongPasswordException();
		}
	}

	//	public String findPasswordByPhone(String id, String name, String phone) {
//		return userRepository.findPasswordByPhone(id, name, phone);
//	}
//
//	public String findPasswordByEmail(String id, String name, String email) {
//		return userRepository.findPasswordByEmail(id, name, email);
//	}
}
