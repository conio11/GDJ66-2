package com.goodee.mvcboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcboard.service.BoardService;
import com.goodee.mvcboard.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j // static 변수 사용 가능 // 어느 클래스든 static log() 메소드 사용 가능 -> 디버깅 (매개변수: String)
@Controller
public class BoardController {
	/*
    ANSI_RESET = "\u001B[0m";
    ANSI_BLACK = "\u001B[30m";
    ANSI_RED = "\u001B[31m";
    ANSI_GREEN = "\u001B[32m";
    ANSI_YELLOW = "\u001B[33m";
    ANSI_BLUE = "\u001B[34m";
    ANSI_PURPLE = "\u001B[35m";
    ANSI_CYAN = "\u001B[36m";
    ANSI_WHITE = "\u001B[37m";
    ANSI_BLACK_BACKGROUND = "\u001B[40m";
    ANSI_RED_BACKGROUND = "\u001B[41m";
    ANSI_GREEN_BACKGROUND = "\u001B[42m";
    ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    ANSI_BLUE_BACKGROUND = "\u001B[44m";
    ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    ANSI_CYAN_BACKGROUND = "\u001B[46m";
    ANSI_WHITE_BACKGROUND = "\u001B[47m";
	*/
	
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/addBoard")
	public String addBoard() {
		return "/board/addBoard";
	}
	
	
	/*
	@PostMapping("/board/addBoard")
	public String addBoard(Board board) {
		boardService.addBoard(board);
		// String strRow = row + "";
		// log.debug("\u001B[32m" + strRow);
		return "redirect:/board/boardList";	
	}
	*/
	
	@PostMapping("/board/addBoard")
	public String addBoard(Board board) {
		int row = boardService.addBoard(board);
		System.out.println(row + " <-- row(addBoard)");
		log.debug("\u001B[31m" + "row : " + row + "\u001B[0m"); // 로그 출력 문자열 색상지정
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/modifyBoard")
	public String modifyBoard(Model model, int boardNo) {
		Board board = boardService.selectBoardOne(boardNo);
		
		model.addAttribute("board", board);
		
		return "/board/modifyBoard";
	}
	
	@PostMapping("/board/modifyBoard")
	public String modifyBoard(Board board) {
		int row = boardService.modifyBoard(board);
		System.out.println(row + "<-- row(modifyBoard)");
		log.debug("\u001B[31m" + "row : " + row + "\u001B[0m"); 
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/removeBoard")
	public String removeBoard(Model model, int boardNo) {
		Board board = boardService.selectBoardOne(boardNo);
		
		model.addAttribute("board", board);
		
		return "/board/removeBoard";
	}
	
	@PostMapping("/board/removeBoard")
	public String removeBoard(Board board) {
		int row = boardService.removeBoard(board);
		System.out.println(row + " <-- row(removeBoard)");
		log.debug("\u001B[31m" + "row : " + row + "\u001B[0m"); 
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/boardOne")
	public String boardOne(Model model, int boardNo) {
		// model.addAttribute();
		// 여기서 service로 받고 모델에 설정
		
		Board board = boardService.selectBoardOne(boardNo);
		
		model.addAttribute("board", board);
		
		return "/board/boardOne";
	}

	@GetMapping("/board/boardList")
	public String boardList(Model model, @RequestParam(name = "currentPage", defaultValue="1") int currentPage, 
										 @RequestParam(name = "rowPerPage", defaultValue="10") int rowPerPage, 
										 @RequestParam(name = "localName", required = false) String localName) { // spring model 생명주기: request와 같음 (session은 따로 설정)
		
		log.debug("\u001B[31m" + "localName : " + localName + "\u001B[0m");
		System.out.println("localName : " + localName);
		
		Map<String, Object> resultMap = boardService.getBoardList(currentPage, rowPerPage, localName);
		
		// view로 넘길 때는 다시 분리해서 넘김
		model.addAttribute("localNameList", resultMap.get("localNameList"));
		model.addAttribute("boardList", resultMap.get("boardList"));
		
		// List<Map<String, Object>> resultMap = boardService.getBoardList();
		//model.addAttribute("localNameList", localNameList); // request.setAttribute() 와 비슷한 역할
		
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		
		return "/board/boardList";
	}
}
