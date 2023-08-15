package com.goodee.myJoinTree.vo;

import lombok.Data;

@Data
public class AccountList {
	private int empNo;
	private String actice;
	private String empPw;
	private String createdate;
	private String updatedate;
	private int createId;
	private int updateId;
	
	private String newPw; // 테이블에 컬럼 존재 X, 비밀번호 변경 시 임시 사용
}