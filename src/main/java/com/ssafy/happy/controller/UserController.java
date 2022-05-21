package com.ssafy.happy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ssafy.happy.dto.User;
import com.ssafy.happy.model.service.JwtService;
import com.ssafy.happy.model.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final JwtService jwtService;

	@Autowired
	public UserController(UserService userService, JwtService jwtService) {
		this.userService = userService;
		this.jwtService = jwtService;
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		System.out.println("user = " + user);
		User searchedUser = userService.login(user.getId());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		try {
			if (searchedUser != null && searchedUser.getPassword().equals(user.getPassword())) {
				String token = jwtService.createToken("id", searchedUser.getId(), "access-token");
				log.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", "success");
			} else {
				resultMap.put("message", "fail");
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			log.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable String id, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			log.info("사용 가능한 토큰!!!");
			try {
				User user = userService.login(id);
				resultMap.put("userInfo", user);
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
	public ResponseEntity<String> join(@RequestBody User user) {
		log.debug(user.toString());
		if(userService.register(user) > 0) {
			return new ResponseEntity<>("success", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//
	// @GetMapping("/logout")
	// public String logout(HttpSession session) {
	// 	session.invalidate();
	// 	return "redirect:/";
	// }

	@PutMapping("/")
	public ResponseEntity<String> update(@RequestBody User user)  {
		if(userService.updateAccount(user) > 0) {
			return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id)  {
		if(userService.deleteAccount(id) > 0) {
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
