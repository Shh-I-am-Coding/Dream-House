package com.ssafy.happy.user.controller;

import com.ssafy.happy.common.dto.ApiResponse;
import com.ssafy.happy.user.dto.UserJoinRequest;
import com.ssafy.happy.user.dto.UserLoginRequest;
import com.ssafy.happy.user.dto.UserLoginResponse;
import com.ssafy.happy.user.dto.UserResponse;
import com.ssafy.happy.user.service.KakaoService;
import com.ssafy.happy.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final KakaoService kakaoService;

	@PostMapping("/confirm-password/{id}")
	public ResponseEntity<ApiResponse<String>> confirmPassword(@PathVariable Long id, @RequestBody String password) {
		userService.confirmPassword(id, password);
		return new ResponseEntity<>(ApiResponse.ok(true), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<UserLoginResponse>> login(@RequestBody UserLoginRequest userLoginRequest) {
		return new ResponseEntity<>(ApiResponse.of(true, userService.login(userLoginRequest), "success"), HttpStatus.OK);
	}

	@PostMapping("/login/oauth/kakao")
	public ResponseEntity<ApiResponse<UserLoginResponse>> kakaoLogin(@RequestBody String code) {
		return new ResponseEntity<>(ApiResponse.of(true, kakaoService.login(code), "success"), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponse>> getInfo(@PathVariable Long id, HttpServletRequest request) {
		return new ResponseEntity<>(ApiResponse.of(true, userService.findUser(id), "success"), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<String>> join(@RequestBody UserJoinRequest userJoinRequest) {
		userService.join(userJoinRequest);
		return new ResponseEntity<>(ApiResponse.ok(true), HttpStatus.OK);
	}

	@PostMapping("/send-email")
	public ResponseEntity<ApiResponse<String>> sendEmail(@RequestBody String email) {
		return new ResponseEntity<>(ApiResponse.of(true, userService.sendEmail(email), "success"), HttpStatus.OK);
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

//	@PostMapping("/findPasswordByPhone")
//	public String findPasswordByPhone(String id, String name, String phone, Model model) {
//		String pw = userService.findPasswordByPhone(id, name, phone);
//		if (pw != null) {
//			model.addAttribute("msg", "비밀번호는 " + pw + "입니다.");
//			return "/user/login";
//		} else {
//			model.addAttribute("msg", "회원정보가 올바르지 않습니다.");
//			return "/user/findPassword";
//		}
//	}
//
//	@PostMapping("/findPasswordByEmail")
//	public String findPasswordByEmail(String id, String name, String email, Model model) {
//		String pw = userService.findPasswordByEmail(id, name, email);
//		if (pw != null) {
//			model.addAttribute("msg", "비밀번호는 " + pw + "입니다.");
//			return "/user/login";
//		} else {
//			model.addAttribute("msg", "회원정보가 올바르지 않습니다.");
//			return "/user/findPassword";
//		}
//	}
}
