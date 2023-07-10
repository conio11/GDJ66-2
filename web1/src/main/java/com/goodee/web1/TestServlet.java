package com.goodee.web1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Servlet 기능
public class TestServlet {
	@GetMapping("/home")
	public String home() {
		System.out.println("/home 실행"); // 요청 메소드 + URL 패턴
		TestVo vo = new TestVo();
		vo.setX(7); // lombok 실행 확인
		vo.setY(9);
		
		System.out.println(vo);
		
		
		return "home"; 
		// application.properties 파일은 설정과 합쳐진 반환 문자열이 포워딩 주소가 됨
	}
}