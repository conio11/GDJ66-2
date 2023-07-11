package com.goodee.web1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Servlet 기능
public class TestServlet {
	@GetMapping("/home") // HTTP GET 요청이 "/home" 경로로 들어왔을 때 메소드 실행 -> 클라이언트가 "/home" 경로로 GET 요청을 보내면 home() 메소드 실행
	public String home() {
		System.out.println("/home 실행"); // 요청 메소드 + URL 패턴
		TestVo vo = new TestVo();
		vo.setX(7); // lombok 실행 확인
		vo.setY(9);
		
		System.out.println(vo);
		
		return "home";  // 스프링 부트의 View Resolver에 의해 처리 -> "home.html" 또는 "home.jsp"와 같은 템플릿 파일을 찾아 렌더링
		// application.properties 파일은 설정과 합쳐진 반환 문자열이 포워딩 주소가 됨
	}
}