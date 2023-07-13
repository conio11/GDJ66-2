package com.goodee.mvcboard.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j // log static 객체를 사용하기 위함
@Controller
public class UUIDTest {
	@GetMapping("uuidTest")
	public String uuidTest() {
		UUID uuid = UUID.randomUUID(); //
		log.debug(uuid.toString().replace("-", ""));
		// log.debug(uuid.toString());
		return "";
	}
}