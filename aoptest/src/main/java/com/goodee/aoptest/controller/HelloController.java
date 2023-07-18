package com.goodee.aoptest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HelloController {
	static final String CYAN = "\u001B[46m";
	static final String RESET = "\u001B[0m";
	
	@GetMapping("/goodee/one")
	public String one() {
		log.debug(CYAN + "HelloController.one()" + RESET);
		return "";
	}
	
	@GetMapping("/goodee/two")
	public String two() {
		log.debug(CYAN + "HelloController.two()" + RESET);
		return "";
	}
}
