package com.goodee.webdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.webdb.mapper.StudentMapper;
import com.goodee.webdb.vo.Student;

// 서비스 - 메소드에서 예외 발생 시 롤백
@Transactional // 이 클래스 내 메소드가 하나라도 실패 시 모두 취소 (Spring에서 DB로 명령이 아예 전달되지 않음)
@Service
public class StudentService {
	@Autowired private StudentMapper studentMapper; // new() 호출할 클래스 만들어지지 않음(interface) -> 부모 타입 사용

	public List<Student> getStudentAll() {
		// studentMapper = new StudentMapper(); // StudentMapper: 인터페이스이므로 객체 생성 불가
		List<Student> list = studentMapper.selectStudentAll();  
		return list;
	}
}