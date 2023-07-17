package com.goodee.cash.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.cash.service.CashService;
import com.goodee.cash.vo.Cashbook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CashController {
	@Autowired private CashService cashService;
	
	@GetMapping("/calendar")
	public String calendar(HttpSession session, Model model, @RequestParam(required = false, name = "targetYear") Integer targetYear,
															 @RequestParam(required = false, name = "targetMonth") Integer targetMonth) {
		log.debug("CashController.calendar() param targetYear: " + targetYear);
		log.debug("CashController.calendar() param targetMonth: " + targetMonth);
		
		// 세션에서 로그인 된 memberId 추출
		// session.getAttribute("loginMember");
		String memberId = "user";
		Map<String, Object> resultMap = cashService.getCalendar(memberId, targetYear, targetMonth);
		
		log.debug("CashController.calendar() resultMap : " + resultMap.toString());
		
		/*
		model.addAttribute("targetYear", resultMap.get("targetYear"));
		model.addAttribute("targetMonth", resultMap.get("targetMonth"));
		model.addAttribute("beginBlank", resultMap.get("beginBlank"));
		model.addAttribute("endBlank", resultMap.get("endBlank"));
		model.addAttribute("totalTd", resultMap.get("totalTd"));
		model.addAttribute("lastDate", resultMap.get("lastDate"));
		*/
		model.addAttribute("resultMap", resultMap);
		
		
		// cashService.getCalendar();
		return "calendar";
	}
}
