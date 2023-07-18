package com.goodee.aoptest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component // 객체 생성
public class MyAspect {
	// AOP : 메소드를 가로챔
	final String CYAN = "\u001B[46m";
	final String RESET = "\u001B[0m";
	
	@Before("execution(* com.goodee.aoptest.controller.HelloController.one(..))") // one(..) -> one 메소드 내 모든 요소
	public void beforeLog() {
		log.debug(CYAN + "(전)AOP" + RESET);
	}
	
	@After("execution(* com.goodee.aoptest.controller.HelloController.one(..))") // one(..) -> one 메소드 내 모든 요소
	public void afterLog() {
		log.debug(CYAN + "(후)AOP" + RESET);
	}
}
