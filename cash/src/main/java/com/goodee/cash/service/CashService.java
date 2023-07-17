package com.goodee.cash.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.cash.mapper.CashMapper;
import com.goodee.cash.vo.Cashbook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CashService {
	static final String CYAN = "\u001B[46m";
	static final String RESET = "\u001B[0m";
	
	@Autowired
	private CashMapper cashMapper;
	
	// 요청단에서 타겟 (year, month) 넘어오지 않으면 -1
	public Map<String, Object> getCalendar(String memberId, Integer targetYear, Integer targetMonth) { // null 사용하기 위해 Integer
		Calendar firstDate = Calendar.getInstance(); // 첫 번째 날짜(1일)의 요일을 구하기 위함
		firstDate.set(Calendar.DATE, 1); // 현재 월의 1일 
		
		if (targetYear != null && targetMonth != null) { // year, month 값이 모두 넘어오면
			firstDate.set(Calendar.YEAR, targetYear);
			firstDate.set(Calendar.MONTH, targetMonth); // -1 입력 시 -> year - 1, month: 11, 12 입력 시 -> year + 1, month: 0 API가 자동 변환 처리	
		} 
		targetYear = firstDate.get(Calendar.YEAR);
		targetMonth = firstDate.get(Calendar.MONTH);
		
		// 마지막 날짜
		int lastDate = firstDate.getActualMaximum(Calendar.DATE);
		
		// 1일의 요일을 이용하여 1일 앞 빈칸의 수 세팅 (일요일:1, 토요일: 7) -> (요일 - 1)개
		int beginBlank = firstDate.get(Calendar.DAY_OF_WEEK) - 1;
		log.debug(CYAN + "beginBlank : " + beginBlank + RESET);
		System.out.println("beginBlank : " + beginBlank);
		
		// 마지막 날짜 이후 빈칸 수
		int endBlank = 0; // 7로 나누어 떨어질 경우 0
		if ((beginBlank + lastDate) % 7 != 0) {
			endBlank = 7 - ((beginBlank + lastDate) % 7);
		}
		
		// 전체 칸 수 
		int totalTd = beginBlank + lastDate + endBlank; 
		
		// Mapper 매개값
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("memberId", memberId);
		paramMap.put("targetYear", targetYear);
		paramMap.put("targetMonth", targetMonth + 1); // targetMonth: 0 ~ 11 -> DB 저장 시 1 더함
		
		List<Cashbook> cashbookList = cashMapper.selectCashbookListByMonth(paramMap);
		log.debug(CYAN + "CashService.getCalendar() cashbookList : " + cashbookList.toString() + RESET);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("cashbookList", cashbookList);
		resultMap.put("targetYear", targetYear);
		resultMap.put("targetMonth", targetMonth);
		resultMap.put("lastDate", lastDate);
		resultMap.put("beginBlank", beginBlank);
		resultMap.put("endBlank", endBlank);
		resultMap.put("totalTd", totalTd);
		
		log.debug(CYAN + "CashService.getCalendar() resultMap : " + resultMap.toString() + RESET);
		return resultMap;
	}	
}