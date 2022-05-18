package com.ssafy.happy.controller;

import java.security.PrivateKey;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happy.dto.User;
import com.ssafy.happy.model.service.UserService;
import com.ssafy.happy.util.RSAEncryption;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}

	@PostMapping("/login")
	@ResponseBody
	public boolean login(String id, String password, HttpSession session) throws SQLException {
		//암호화된 id,password를 개인키로 복호화
		PrivateKey privateKey = (PrivateKey)session.getAttribute("RSA_WEB_Key");
		String uid = RSAEncryption.decrypt(privateKey, id);
		String pwd = RSAEncryption.decrypt(privateKey, password);

		User user = userService.login(uid);
		if(user != null && user.getPassword().equals(pwd)) {
			session.setAttribute("user", user);
			return true;
		} else {
			return false;
		}
	}

	@GetMapping("/join")
	public String join() {
		return "/user/join";
	}

	@PostMapping("/join")
	public String join(User user) throws SQLException{
		userService.register(user);
		return "/user/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("/update")
	public String update(User user, Model model, HttpSession session) throws SQLException {
		userService.updateAccount(user);
		System.out.println(session);
		session.setAttribute("user", user);
		model.addAttribute("msg", "회원정보가 수정되었습니다.");
		return "redirect:/";
    }

	@GetMapping("/delete")
	public String delete(String id, Model model, HttpSession session) throws SQLException {
		userService.deleteAccount(id);
		session.invalidate();
		model.addAttribute("msg", "회원탈퇴가 되었습니다.");
		return "redirect:/";
	}

	@GetMapping("/myInfo")
	public String myInfo(HttpSession session) {
		return "/user/myInfo";
	}

	@GetMapping("/findPassword")
	public String findPassword() {
		return "/user/findPassword";
	}

	@PostMapping("/findPasswordByPhone")
	public String findPasswordByPhone(String id, String name, String phone, Model model) {
		String pw = userService.findPasswordByPhone(id, name, phone);
		if(pw != null) {
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
