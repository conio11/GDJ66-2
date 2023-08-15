<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>resetPw</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				const urlParams = new URL(location.href).searchParams;
				const msg = urlParams.get("msg");
					if (msg != null) {
						alert(msg);
					}
					
				// 초기 비밀번호 입력/확인 필드 비활성화
				$("#newPw1").prop("disabled", true); // .prop: 요소의 속성값(property)을 가져오거나 설정
				$("#newPw2").prop("disabled", true);
				
				// 인증 버튼 클릭 시 
				$("#authBtn").click(function() {
					// 인증 조건 확인 로직 추가
					// 조건 충족 시 비밀번호 입력/확인 필드 활성화
					$("#newPw1").prop("disabled", false);
					$("#newPw2").prop("disabled", false);
				});
				
				// <input type="number"> 일 경우 숫자만 입력받도록 설정
				$("input[type='number']").on("keypress", function(event) {
					if ((event.which < 48) || (event.which > 57)) {
						return false;
					}
			    });
				
			});
		</script>
	</head>
	<body>
		<h1>비밀번호 재설정</h1>
		
		<div>
			<a href="/myJoinTree/home">홈</a>
		</div>
		<!-- <form action="/myJoinTree/resetPw" method="post"> -->
			<div>
				<label for="empNo">사번</label>
				<input type="number" name="empNo" id="empNo">
			</div>
			
			<div>
				<label for="newPw1">주민등록번호 뒷자리</label>
				<input type="number" name="juminNo" id="juminNo">
			</div>
			
			<button id="authBtn">인증</button>
			
			<div>
				<label for="newPw1">새 비밀번호 입력</label>
				<input type="password" name="newPw1" id="newPw1">
			</div>
			<div>
				<label for="newPw2">새 비밀번호 확인</label>
				<input type="password" name="newPw2" id="newPw2">
			</div>
			
			<button id="resetPwBtn">비밀번호 재설정</button>
		<!-- </form> -->
	
	</body>
</html>