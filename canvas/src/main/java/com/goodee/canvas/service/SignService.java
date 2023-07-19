package com.goodee.canvas.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.canvas.mapper.SignMapper;
import com.goodee.canvas.restapi.SignController;
import com.goodee.canvas.vo.Sign;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SignService {
	final String CYAN = "\u001B[46m";
	final String RESET = "\u001B[0m";
	
	@Autowired
	SignMapper signMapper;
	
	public void addSign(String sign, String path) {
		String type = sign.split(";")[0].split(":")[1]; // split: 구분자를 기준으로 문자열 배열 반환
		String data = sign.split(",")[1];
		byte[] image = Base64.decodeBase64(data);
		int size = image.length;
		
		log.debug(CYAN + type + " <-- type(SignService)"  + RESET);
		log.debug(CYAN + size + " <-- size(SignService)"  + RESET);
		
		// 저장 시 사용할 파일명
		String saveFilename = UUID.randomUUID().toString().replace("-", "") + ".png"; // UUID에서 하이픈 제거
		log.debug(CYAN + saveFilename + " <-- saveFilename(SignService)"  + RESET);
		
		// DB에 정보 저장
		Sign s = new Sign();
		s.setSignMember("user");
		s.setSignSaveFilename(saveFilename);
		s.setSignFiletype(type);
		s.setSignFilesize(size);
		
		signMapper.insertSign(s);
		
		// 빈 파일 생성
		File f = new File(path + saveFilename);
		
		  try {
			  // 빈 파일에 이미지 파일 주입
			  FileOutputStream fos = new FileOutputStream(f); // 파일에 바이트를 기록하는 클래스
	          fos.write(image); // 디코딩된 데이터 파일에 저장
	          fos.close();
	          log.debug(CYAN + f.length() + " <-- f.length()(SignService)"  + RESET); 
		  } catch (IllegalStateException | IOException e) {
	            e.printStackTrace();
	            // 트랜잭션 작동을 위해 예외를 강요하지 않는 예외 발생 필요
	            throw new RuntimeException();
	      }		 
	}
}