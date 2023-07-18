package com.goodee.aoptest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ServletComponentScan
@SpringBootApplication
public class AoptestApplication implements WebMvcConfigurer { // 애플리케이션 클래스가 인터페이스 상속
	@Autowired
	// MyInterceptor myInterceptor;
	HandlerInterceptor myInterceptor; // 부모 인터페이스 사용으로 결합도 낮춤

	public static void main(String[] args) {
		SpringApplication.run(AoptestApplication.class, args);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 사용자가 만든 인터셉터를 registry에 등록
		registry.addInterceptor(myInterceptor).addPathPatterns("/goodee/*"); // super 위에 위치
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}