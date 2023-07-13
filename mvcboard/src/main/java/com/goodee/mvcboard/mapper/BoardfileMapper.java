package com.goodee.mvcboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Boardfile;

@Mapper
public interface BoardfileMapper {
	int insertBoardfile(Boardfile boardfile);
	
	// 업로드 파일 상세정보 - 한 게시글에 여러 파일의 정보 담길 수 있으므로 List
	List<Boardfile> selectBoardfileOne(int boardNo);
	
	// 파일 수정
	
	
	// 파일 삭제
	
}
