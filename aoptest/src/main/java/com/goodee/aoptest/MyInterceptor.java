package com.goodee.aoptest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // 인터셉트 작동을 위해 지정 // 인터셉터: 컨트롤러를 가로챔
public class MyInterceptor implements HandlerInterceptor { // @ 방식이 아닌 인터페이스 상속
	final String CYAN = "\u001B[46m";
	final String RESET = "\u001B[0m";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug(CYAN + "(전)인터셉터" + RESET);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.debug(CYAN + "(후)인터셉터" + RESET);
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}	
}