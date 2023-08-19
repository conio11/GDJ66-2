package com.goodee.myJoinTree.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.myJoinTree.service.EmpInfoService;
import com.goodee.myJoinTree.service.LoginService;
import com.goodee.myJoinTree.vo.AccountList;
import com.goodee.myJoinTree.vo.EmpInfo;
import com.goodee.myJoinTree.vo.EmpInfoImg;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmpInfoController {
	static final String CYAN = "\u001B[46m";
	static final String RESET = "\u001B[0m";
	
	String msg = "";
	@Autowired 
	private EmpInfoService empInfoService;
	
	// 비밀번호 변경 페이지로 이동
	@GetMapping("/empInfo/modifyPw")
	public String modifyPw() {
	    return "/empInfo/modifyPw"; 
	}
	
	// 비밀번호 변경 액션
	@PostMapping("/empInfo/modifyPw")
	public String modifyPw(HttpSession session, @RequestParam("empPw") String empPw, 
			@RequestParam("newPw") String newPw) throws UnsupportedEncodingException {
		AccountList loginAccount = (AccountList) session.getAttribute("loginAccount");
		
		int empNo = loginAccount.getEmpNo();
		
		// 비밀번호 변경 로직 호출
		AccountList account = new AccountList();
		account.setEmpNo(empNo);
		account.setEmpPw(empPw);
		account.setNewPw(newPw);
		account.setUpdateId(empNo);
		
		int row = empInfoService.modifyPw(account);
		log.debug(CYAN + row + " <-- row(EmpInfoController-modifyPw)" + RESET);
		
		if (row == 1) {
			msg = URLEncoder.encode("비밀번호가 변경되었습니다. 다시 로그인 후 이용 가능합니다.", "UTF-8");
			session.setAttribute("pwChangeMessage", msg); // 메시지를 세션에 저장
			
			return "redirect:/logout?msg=" + msg;
		} else {
			msg = URLEncoder.encode("비밀번호 변경 실패. 현재 비밀번호를 확인해주세요.", "UTF-8");
			return "redirect:/empInfo/modifyPw?msg=" + msg;
		}
	}
	
	@GetMapping("/empInfo/empInfo")
	public String empInfo(HttpSession session, Model model) {
		AccountList loginAccount = (AccountList) session.getAttribute("loginAccount");
		
		int empNo = loginAccount.getEmpNo();
		
		Map<String, Object> map = empInfoService.getEmpOne(empNo);
		
		log.debug(CYAN + map + " <-- map(EmpInfoController-empInfo)" + RESET);
		
		// EmpInfo empInfo = (EmpInfo)map.get("empInfo");
		// EmpInfoImg empInfoImg = (EmpInfoImg)map.get("empInfoImg");
	
		model.addAttribute("empInfo", map);
		// model.addAttribute("empInfoImg", empInfoImg);
		
	    return "/empInfo/empInfo"; // 나의 정보 페이지로 이동
	}
	
	@GetMapping("/empInfo/checkPw")
	public String checkPw() {
	    return "/empInfo/checkPw"; // 나의 정보 수정 전 비밀번호 체크 페이지
	}
	
	@PostMapping("/empInfo/checkPw")
	public String checkPw(HttpSession session, @RequestParam("empPw") String empPw) throws UnsupportedEncodingException {
		AccountList loginAccount = (AccountList) session.getAttribute("loginAccount");
		
		int empNo = loginAccount.getEmpNo();
		
		int row = empInfoService.selectCheckPw(empNo, empPw);
		log.debug(CYAN + row + " <-- row(EmpInfoController-checkPw)" + RESET);
		
		if (row == 1) { // 비밀번호 일치
			return "redirect:/empInfo/modifyEmp";
		} else {
			msg = URLEncoder.encode("비밀번호가 일치하지 않습니다.", "UTF-8");
			return "redirect:/empInfo/checkPw?msg=" + msg;
		}
	}
	
	@GetMapping("/empInfo/modifyEmp")
	public String modifyEmp(HttpSession session, Model model) {
	    AccountList loginAccount = (AccountList) session.getAttribute("loginAccount");
	    int empNo = loginAccount.getEmpNo();
	    
	    Map<String, Object> map = empInfoService.getEmpOne(empNo);
	    
		log.debug(CYAN + map + " <-- map(EmpInfoController-modifyEmp)" + RESET);
	    
	    // EmpInfo empInfo = (EmpInfo) map.get("empInfo");
	    // EmpInfoImg empInfoImg = (EmpInfoImg) map.get("empInfoImg");
	    
	    // model.addAttribute("empInfo", empInfo);
	    // model.addAttribute("empInfoImg", empInfoImg);
	    
		// EmpInfo empInfo = (EmpInfo)map.get("empInfo");
		// EmpInfoImg empInfoImg = (EmpInfoImg)map.get("empInfoImg");
		
		
		model.addAttribute("empInfo", map);
		// model.addAttribute("empInfoImg", empInfoImg);
	    
	    return "/empInfo/modifyEmp"; // 나의 정보 수정 페이지로 이동
	}
	
	@PostMapping("/empInfo/modifyEmp")
	public String modifyEmp(HttpSession session, Model model, 
					@RequestParam Map<String, Object> empInfo) throws UnsupportedEncodingException {
		AccountList loginAccount = (AccountList) session.getAttribute("loginAccount");
		
		int empNo = loginAccount.getEmpNo();
		
		empInfo.put("empNo", empNo);
		
		int row = empInfoService.modifyEmp(empInfo);
		log.debug(CYAN + row + " <-- row(EmpInfoController-modifyEmp)" + RESET);
		
		if (row == 1) {
			msg = URLEncoder.encode("사원 정보가 변경되었습니다.", "UTF-8");
			session.setAttribute("empName", empInfo.get("empName"));
			
			return "redirect:/empInfo/empInfo?msg=" + msg;
		} else {
			msg = URLEncoder.encode("사원 정보 변경 실패. 관리자에게 문의해주세요.", "UTF-8");
			return "redirect:/empInfo/modifyEmp?msg=" + msg;
		}
	}
	
	@PostMapping("/empInfo/modifyEmp/uploadEmpImg")
	@ResponseBody
	public String uploadEmpImg(@RequestParam("newImgFile") MultipartFile newImgFile, HttpServletRequest request, HttpSession session, Model model) {
		AccountList loginAccount = (AccountList) session.getAttribute("loginAccount");
		
		int empNo = loginAccount.getEmpNo();
		
		// 파일 저장 경로 설정
		String path = request.getServletContext().getRealPath("/empImg/"); // 실제 파일 시스템 경로
		
		int row = empInfoService.uploadEmpImg(empNo, newImgFile, path);
		
		if (row == 1) {
			return "success";
		} else {
			return "error";
		}
	}
	
	@PostMapping("/empInfo/modifyEmp/modifyEmpImg")
	@ResponseBody
	public String modifyEmpImg(HttpServletRequest request, HttpSession session, Model model) {
		int row = 1;
		
		if (row == 1) {
			return "success";
		} else {
			return "false";
		} 
	}
	
	
}