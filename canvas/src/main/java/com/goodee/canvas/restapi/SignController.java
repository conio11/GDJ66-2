package com.goodee.canvas.restapi;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.canvas.service.SignService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SignController {
	final String CYAN = "\u001B[46m";
	final String RESET = "\u001B[0m";
	
	@Autowired
	SignService signService;
	
	@PostMapping("/addSign")
	public String addSign(HttpServletRequest request, @RequestParam(name="sign") String sign) {
		// Service Layer: BASE64 디코딩 -> 이미지로 변경 -> 저장소에 이미지 저장 -> DB에 메타데이터 저장
		String path = request.getServletContext().getRealPath("/sign/");
		log.debug(CYAN + sign + " <-- SignController.addSign() param sign" + sign + RESET);
		signService.addSign(sign, path);
		
		return "YES";
	}
}