package com.goodee.aoptest;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/goodee/*")
public class GoodeeFilter extends HttpFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 톰캣이 주소 맵핑을 받았을 때,,
		final String CYAN = "\u001B[46m";
		final String RESET = "\u001B[0m";
		
		// 전
		log.debug(CYAN + "전(필터)" + RESET);
		
		chain.doFilter(request, response);
		
		// 후
		log.debug(CYAN + "후(필터)" + RESET);
	}
}