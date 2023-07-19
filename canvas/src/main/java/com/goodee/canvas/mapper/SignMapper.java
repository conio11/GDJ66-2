package com.goodee.canvas.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.canvas.vo.Sign;

@Mapper
public interface SignMapper {
	public int insertSign(Sign sign);
}