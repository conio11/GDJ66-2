package com.goodee.myJoinTree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.myJoinTree.mapper.LoginMapper;
import com.goodee.myJoinTree.vo.AccountList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LoginService {
	@Autowired
	private LoginMapper loginMapper;
	
	// 로그인
	public AccountList login(AccountList account) {
		AccountList accountEmp = loginMapper.selectAccount(account);
		return accountEmp;
	}
}