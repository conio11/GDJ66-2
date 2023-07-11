package com.goodee.mvcboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Board;

@Mapper // 구현 클래스의 객체를 만들기 위해 필요
public interface BoardMapper {
	// local_name 컬럼, count() 반환
	List<Map<String, Object>> selectLocalNameList();
	
	// myBatis 메소드는 매개값을 하나만 허용
	// param: Map<String, Object> map -> int beginRow, int rowPerPage
	List<Board> selectBoardListByPage(Map<String, Object> map); 
	
	
	// 전체 행 개수
	int selectBoardCnt();
	
}