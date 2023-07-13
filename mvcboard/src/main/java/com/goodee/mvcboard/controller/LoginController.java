package com.goodee.mvcboard.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
// @SessionAttributes(names = "") // 모델 생명 주기 -> 세션
public class LoginController {
	@PostMapping("/login")
	public String login(HttpSession session,  
						@RequestParam(name="memberId") String memberId, 
						@RequestParam(name="memberPw") String memberPw) {
		// service(memberId, memberPw) -> mapper -> 로그인 성공 유무 반환
		
		// 로그인 성공 시 
		// model.addAttribute(memberPw);
		session.setAttribute("", "");
		
		// 동일한 이름일 경우 (name="memberId") 생략 가능
		
		
		return "";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
			session.invalidate();
			
			return "";
	}
}
