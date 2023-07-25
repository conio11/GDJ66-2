package com.goodee.excelNew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelController {
	@GetMapping("/excel")
	public List<Map<String, Object>> excel() {
		// Mock 모델
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> m1 = new HashMap<>();
		m1.put("name", "루피");
		m1.put("age", "17");
		list.add(m1);
		
		Map<String, Object> m2 = new HashMap<>();
		m2.put("name", "나미");
		m2.put("age", "19");
		list.add(m2);	
		
		return list;
	}
}

