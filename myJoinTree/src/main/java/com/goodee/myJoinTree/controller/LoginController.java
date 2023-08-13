package com.goodee.myJoinTree.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.myJoinTree.service.LoginService;
import com.goodee.myJoinTree.vo.AccountList;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
//@SessionAttributes(names = "") // 모델 생명 주기 -> 세션
public class LoginController {
	@Autowired 
	private LoginService loginService;
	
	// 로그인 페이지로 이동
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		// loginId(쿠키)가 있는지 확인하여 그 값을 request 속성에 저장
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("loginId") == true) {
					request.setAttribute("loginId", c.getValue());
					// break;
				}
			}
		}
		
		return "login";
	}
	
	// 로그인 액션
	@PostMapping("/login")

	public String login(HttpSession session, HttpServletRequest request,
				  HttpServletResponse response,
				  @RequestParam(name="empNo") int empNo,
				  @RequestParam(name="empPw") String empPw,
				  @RequestParam(name="saveId", required = false) String saveId
			) {
		// sevice(empNo, empPw) -> mapper -> 로그인 성공 유무 반환
		
		AccountList account = new AccountList();
		account.setEmpNo(empNo);
		account.setEmpPw(empPw);
				
		AccountList loginAccount = loginService.login(account);
        
		// 로그인 성공 시 
		if (loginAccount != null) {
			log.debug("아이디 저장"); // 디버그 로그
			session.setAttribute("loginAccount", loginAccount);
			
			// saveId 체크박스가 선택되었을 경우, 사용자 아이디를 쿠키에 저장
			// saveId != null && saveId.equals("y")
			if (saveId != null) {
				 Cookie loginIdCookie = new Cookie("loginId", String.valueOf(empNo));
				 loginIdCookie.setMaxAge(24 * 60 * 60); // 초단위 // 60 * 60 * 24 -> 1일  
				 // loginIdCookie.setPath("/login"); // /login 및 하위 경로에서만 유효하게 설정
				 response.addCookie(loginIdCookie);
			}
			
			return "redirect:/home"; // 사용자가 로그인 페이지의 URL을 유지하지 않도록 리다이렉트
		} else {
			return "/login"; // 로그인 실패 시 로그인 페이지로 이동
		}
		
		
		// model.addAttribute(empPw);
		// 동일한 이름일 경우 (name="empNo") 생략 가능
	}
	
	@GetMapping("/logout")
	public String logout (HttpSession session) {
		session.invalidate();
		return "/login"; // login.jsp로 이동
	}
}
