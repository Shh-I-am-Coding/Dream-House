package com.ssafy.happy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafy.happy.util.RSAEncryption;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(HttpSession session) {
		//로그인 안됐을 시에 공개키, 개인키 설정
		if(session.getAttribute("user") == null) {
			String[] publicKey = RSAEncryption.setPublicKey(session);
			String publicKeyModulus = publicKey[0];
			String publicKeyExponent = publicKey[1];

			session.setAttribute("RSAModulus", publicKeyModulus);  //로그인 폼에 Input Hidden에 값을 셋팅하기위해서
			session.setAttribute("RSAExponent", publicKeyExponent);   //로그인 폼에 Input Hidden에 값을 셋팅하기위해서
		}
		return "index";
	}
}
