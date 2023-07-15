package com.goodee.mvcboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

@Mapper
public interface BoardfileMapper {
	// 파일 업로드
	int insertBoardfile(Boardfile boardfile);
	
	// 업로드 파일 상세정보 - 한 게시글에 여러 파일의 정보 담길 수 있으므로 List
	List<Boardfile> selectBoardfile(int boardNo);
	
	
	// 게시글 내 모든 파일 삭제
	int removeBoardfile(int boardNo);
	
	// 게시글 내 파일 삭제 (1개인 경우)
	int removeBoardfileOne(int boardfileNo);
	
	// 게시글 내 첨부파일 수
	int selectBoardfileCnt(int boardNo);
	
}
