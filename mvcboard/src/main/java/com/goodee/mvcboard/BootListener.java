package com.goodee.mvcboard;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("톰캣 시작 시 실행되는 코드");
    }
}