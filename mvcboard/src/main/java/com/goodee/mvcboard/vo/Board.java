package com.goodee.mvcboard.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Board {
	private int boardNo;
	private String localName;
	private String boardTitle;
	private String boardContent;
	private String memberId;
	private String createdate;
	private String updatedate;
	
	// table 속성은 아니고 입력폼 속성 -> BoardForm.class(DTO), Board.class(도메인) 분리해서 사용 가능
	// 입력 시 위 컬럼명과 함께 업로드 파일도 받기 때문에 Board.java 내 위치
	private List<MultipartFile> multipartFile;
}
