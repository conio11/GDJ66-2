package com.goodee.myJoinTree.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.myJoinTree.vo.AccountList;

@Mapper
public interface LoginMapper {
	// 로그인 액션
	AccountList selectAccount(AccountList account);
}