package com.ssafy.happy.user.controller;

import com.ssafy.happy.common.dto.ApiResponse;
import com.ssafy.happy.user.dto.UserJoinRequest;
import com.ssafy.happy.user.dto.UserLoginRequest;
import com.ssafy.happy.user.dto.UserLoginResponse;
import com.ssafy.happy.user.service.EmailService;
import com.ssafy.happy.user.service.KakaoService;
import com.ssafy.happy.user.service.UserService;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final EmailService emailService;
	private final KakaoService kakaoService;


	@PostMapping("/confirm-password/{id}")
	public ResponseEntity<ApiResponse<String>> confirmPassword(@PathVariable Long id, @RequestBody String password) {
		userService.confirmPassword(id, password);
		return new ResponseEntity<>(ApiResponse.ok(true), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<UserLoginResponse>> login(@RequestBody UserLoginRequest userLoginRequest) {
		UserLoginResponse userLoginResponse = userService.login(userLoginRequest);
		return new ResponseEntity<>(ApiResponse.of(true, userLoginResponse, "success"), HttpStatus.OK);
	}

	@PostMapping("/login/oauth/kakao")
	public ResponseEntity<ApiResponse<UserLoginResponse>> kakaoLogin(@RequestBody String code) {
		UserLoginResponse userLoginResponse = kakaoService.login(code);
		return new ResponseEntity<>(ApiResponse.of(true, userLoginResponse, "success"), HttpStatus.OK);
	}

	@GetMapping("/info/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable String id, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			log.info("사용 가능한 토큰!!!");
			try {
				UserJoinRequest userJoinRequest = userService.search(id);
				resultMap.put("userInfo", userJoinRequest);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			resultMap.put("message", "fail");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@PostMapping("/")
	public ResponseEntity<String> join(@RequestBody UserJoinRequest userJoinRequest) {
		log.debug(userJoinRequest.toString());
		if (userService.register(userJoinRequest) > 0) {
			return new ResponseEntity<>("success", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/email/send")
	public String sendEmail(@RequestBody String email) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(email);
		email = element.getAsJsonObject().get("email").getAsString().trim();
		System.out.println(email);
		String certifiedCode;
		try {
			certifiedCode = emailService.sendMail(email);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return certifiedCode;
	}

	@PutMapping("/")
	public ResponseEntity<String> update(@RequestBody UserJoinRequest userJoinRequest) {
		if (userService.updateAccount(userJoinRequest) > 0) {

			return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		if (userService.deleteAccount(id) > 0) {
			return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/findPasswordByPhone")
	public String findPasswordByPhone(String id, String name, String phone, Model model) {
		String pw = userService.findPasswordByPhone(id, name, phone);
		if (pw != null) {
			model.addAttribute("msg", "비밀번호는 " + pw + "입니다.");
			return "/user/login";
		} else {
			model.addAttribute("msg", "회원정보가 올바르지 않습니다.");
			return "/user/findPassword";
		}
	}

	@PostMapping("/findPasswordByEmail")
	public String findPasswordByEmail(String id, String name, String email, Model model) {
		String pw = userService.findPasswordByEmail(id, name, email);
		if (pw != null) {
			model.addAttribute("msg", "비밀번호는 " + pw + "입니다.");
			return "/user/login";
		} else {
			model.addAttribute("msg", "회원정보가 올바르지 않습니다.");
			return "/user/findPassword";
		}
	}
}
