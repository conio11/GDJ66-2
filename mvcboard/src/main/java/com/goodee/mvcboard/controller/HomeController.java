package com.goodee.mvcboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // .. implements servlet
public class HomeController {
	@GetMapping("/home") // web.xml의 url 패턴 매핑 OR 애노테이션 WebServlet
	public String home() {
		return "home"; // RequestDispatcher.forward();
	}
}
