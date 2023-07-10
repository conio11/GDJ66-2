package com.goodee.webdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.webdb.service.StudentService;
import com.goodee.webdb.vo.Student;


@Controller
public class StudentController {
	@Autowired private StudentService studentService; // new 연산자 사용 X // 주입받기 위해 @Autowired
	
	@GetMapping("/getStudentAll")
	public String getStudentAll(Model model) {
		List<Student> list = studentService.getStudentAll();
		model.addAttribute("list", list); // request.setAttribute();
		
		System.out.println("%%%%%%%%%%%%% " + list.size());
		
		return "getStudentAll"; // forward
		
	}
}
