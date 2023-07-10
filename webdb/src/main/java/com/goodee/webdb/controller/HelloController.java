package com.goodee.webdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HelloController {
	@GetMapping("/hello")
	public String hello() {
		log.debug("hello"); // log: static 변수
		System.out.println("hello %%%%%%");
		return "hello";
	}
}
