package com.goodee.webdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.webdb.vo.Student;

@Mapper
public interface StudentMapper {
	List<Student> selectStudentAll();
}
