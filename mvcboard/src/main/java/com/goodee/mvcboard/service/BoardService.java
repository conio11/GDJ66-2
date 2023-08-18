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
	static final String CYAN = "\u001B[46m";
	static final String RESET = "\u001B[0m";
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired 
	BoardfileMapper boardfileMapper;
	
	// REST API chart 호출
	public List<Map<String, Object>> getLocalNameList() {
		return boardMapper.selectLocalNameList();
	}

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
				
				log.debug(CYAN + mf.getOriginalFilename() + RESET);
				log.debug(CYAN + mf.getSize() + RESET);
				log.debug(CYAN + mf.getContentType() + RESET);
				}			
			}
		}
				
		return row;
	}
	
	public int removeBoard(Board board, String path) {
		int boardfileCnt = boardfileMapper.selectBoardfileCnt(board.getBoardNo());
		int row = boardfileMapper.removeBoardfile(board.getBoardNo());
		log.debug(CYAN + row + " <-- row(BoardService-removeBoard)" + RESET);
		if (boardfileCnt == row) {
			row = boardMapper.removeBoard(board);
			
			// 첨부파일 삭제
			List<Boardfile> boardfileList = boardfileMapper.selectBoardfile(board.getBoardNo());
			
			for (Boardfile bf : boardfileList) {
				File f = new File(path + bf.getSaveFilename());
				// 파일이 있을 경우 삭제
				if (f.exists()) {
					f.delete();
					log.debug(CYAN + "파일 삭제(BoardService-removeBoard)" + RESET);
				}
			}
		}
		return row;
	}
	
	public int removeBoardfileOne(int boardfileNo) {
		int row = boardfileMapper.removeBoardfileOne(boardfileNo);
		log.debug(CYAN + row + " <-- row(BoardService-removeBoardfileOne)" + RESET);
		
		return row;
	}

	public int modifyBoard(Board board, String path) {
		int row = boardMapper.modifyBoard(board);
		log.debug(CYAN + row + " <-- row(BoardService-modifyBoard)" + RESET);
		if (row == 1) { // board가 수정되었을 경우
			int boardNo = board.getBoardNo();
			log.debug(CYAN + boardNo + " <-- boardNo(BoardService-modifyBoard)" + RESET);
			List<MultipartFile> fileList = board.getMultipartFile();
			log.debug(CYAN + fileList.size() + RESET); // 첨부파일 개수 확인
			// 첨부파일이 한 개라도 존재할 경우
			if (fileList != null && fileList.size() > 0) {
				// 저장된 파일을 삭제
				List<Boardfile> boardfileList = boardfileMapper.selectBoardfile(board.getBoardNo());
				if (boardfileList != null && boardfileList.size() > 0) {
					for(Boardfile bf : boardfileList) {
						File f = new File(path + bf.getSaveFilename());
						if(f.exists()) {
							f.delete();
						}
					}
					
					// boardfile 테이블에서 파일을 삭제
					boardfileMapper.removeBoardfile(board.getBoardNo());
				}
				
				for (MultipartFile mf : fileList) { // 첨부된 파일의 개수만큼 반복
					if (mf.getSize() > 0) {
						// 확장자
						String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
						// 새 파일명
						String newFilename = UUID.randomUUID().toString().replace("-", "") + ext; // - 를 공백으로 바꿈
					
						Boardfile bf = new Boardfile();
						bf.setBoardNo(boardNo); // 부모 키 값
						bf.setOriginFilename(mf.getOriginalFilename()); // 원본 파일 이름
						bf.setSaveFilename(newFilename); // 저장 파일 이름
						bf.setFiletype(mf.getContentType());
						bf.setFilesize(mf.getSize());
						
						// 테이블에 저장
						boardfileMapper.insertBoardfile(bf);
						
						// 파일 저장 (저장 위치 필요 -> path)
						File f = new File(path + bf.getSaveFilename());
						// 빈 파일에 첨부된 파일의 스트림을 주입
						try {
							mf.transferTo(f);
							
						} catch (IllegalStateException | IOException e) {
							e.printStackTrace();
							// 트랜잭션 작동을 위해 예외(try-catch를 강요하지 않는 예외 -> ex: RuntimeException) 발생 필요
							throw new RuntimeException();
						}
					}
				}
			}
		}
		
		// return boardMapper.modifyBoard(board);
	    return row;
	}
	
	/*
	 * public Board selectBoardOne(int boardNo) { Board board =
	 * boardMapper.selectBoardOne(boardNo); return board; }
	 */
	
	public Map<String, Object> getBoardOne(int boardNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("board", boardMapper.selectBoardOne(boardNo));
		map.put("boardfiles", boardfileMapper.selectBoardfile(boardNo));
		return map;
	}
	
	public List<Boardfile> getBoardfile(int boardNo){
		return boardfileMapper.selectBoardfile(boardNo);
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
