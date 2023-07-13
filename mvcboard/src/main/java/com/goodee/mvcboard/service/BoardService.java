package com.goodee.mvcboard.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.mvcboard.controller.UUIDTest;
import com.goodee.mvcboard.mapper.BoardMapper;
import com.goodee.mvcboard.mapper.BoardfileMapper;
import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log static 객체를 사용하기 위함
@Service // transaction 어노테이션과 직접 관련 X
@Transactional
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired 
	BoardfileMapper boardfileMapper;
	
	public int addBoard(Board board, String path) {
		int row = boardMapper.insertBoard(board);
		
		// board 입력 한개 성공 후 첨부된 파일이 1개라도 있을 경우
		List<MultipartFile> fileList = board.getMultipartFile();
		if (row == 1 && fileList != null && board.getMultipartFile().size() > 0) {
			int boardNo = board.getBoardNo(); // row 이후에 호출
			
			for (MultipartFile mf : fileList) { // 첨부된 파일의 개수만큼 반복
				if (mf.getSize() > 0) { 
				Boardfile bf = new Boardfile();
				bf.setBoardNo(boardNo); // 부모 키값
				bf.setOriginFilename(mf.getOriginalFilename()); // 파일 원본 이름
				bf.setFilesize(mf.getSize()); // 파일 사이즈
				bf.setFiletype(mf.getContentType()); // 파일 타입(MIME)
				// 저장될 파일 이름
				// 확장자
				String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf(".")); // 마지막으로 나타나는 . 의 위치
				
				// 새로운 이름 + 확장자
	
				String newFilename = UUID.randomUUID().toString().replace("-", "") + ext; // - 를 공백으로 바꿈
				bf.setSaveFilename(newFilename);
				
				// 테이블에 저장
				boardfileMapper.insertBoardfile(bf);
				
				// 파일 저장 (저장 위치 필요 -> path)
				File f = new File(path + bf.getSaveFilename()); // path 위치에 저장파일이름으로 빈 파일을 생성
				// 빈 파일에 첨부된 파일의 스트림을 주입
				try {
					mf.transferTo(f);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					// 트랜잭션 작동을 위해 예외(try-catch를 강요하지 않는 예외 -> ex: RuntimeException) 발생 필요
					throw new RuntimeException();
				} 	
				
				log.debug("\u001B[32m" + mf.getOriginalFilename() + "\u001B[0m");
				log.debug("\u001B[32m" + mf.getSize() + "\u001B[0m");
				log.debug("\u001B[32m" + mf.getContentType() + "\u001B[0m");
				}			
			}
		}
				
		return row;
	}
	
	public int removeBoard(Board board) {
		return boardMapper.removeBoard(board);
	}
	
	public int modifyBoard(Board board) {
		return boardMapper.modifyBoard(board);
	}
	
	/*
	 * public Board selectBoardOne(int boardNo) { Board board =
	 * boardMapper.selectBoardOne(boardNo); return board; }
	 */
	
	public Map<String, Object> selectBoardOne(int boardNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("board", boardMapper.selectBoardOne(boardNo));
		map.put("boardfiles", boardfileMapper.selectBoardfileOne(boardNo));
		return map;
	}
	
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String localName) {
		// service layer 역할 1: controller가 넘겨준 매개값을 dao의 매개값에 맞게 가공 
		int beginRow = (currentPage - 1) * rowPerPage;
		// 반환값 1
		List<Map<String, Object>> localNameList = boardMapper.selectLocalNameList();
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("localName", localName);
		
		// 반환값 2
		List<Board> boardList = boardMapper.selectBoardListByPage(paramMap);
		
		int boardCnt = boardMapper.selectBoardCnt();
		// service layer 역할 1: dao에서 반환받은 값을 가공해 controller에 반환

		int lastPage = boardCnt / rowPerPage;
		if (boardCnt % rowPerPage != 0) {
			lastPage += 1;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("localNameList", localNameList);
		resultMap.put("boardList", boardList);
		resultMap.put("lastPage", lastPage);
		
		return resultMap;
				
		// return boardMapper.selectLocalNameList(); // 우클릭 2번 -> refactor - rename
	}
}
