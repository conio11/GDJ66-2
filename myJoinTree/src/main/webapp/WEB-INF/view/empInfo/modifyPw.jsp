<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyPw</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				const urlParams = new URL(location.href).searchParams;
				const msg = urlParams.get("msg");
					if (msg != null) {
						alert(msg);
					}
			});
	  	</script>
	</head>
	<body>
		<div>
			<a href="/myJoinTree/home">홈</a>
		</div>
		<h1>비밀번호 변경</h1>
		<form action="/myJoinTree/empInfo/modifyPw" method="post">
			<label for="empPw">현재 비밀번호 입력</label>
			<input type="password" name="empPw" id="empPw">
			
			<label for="newPw1">새 비밀번호 입력</label>
			<input type="password" name="newPw1" id="newPw1">
			
			<label for="newPw2">새 비밀번호 다시 입력</label>
			<input type="password" name="newPw2" id="newPw2">
		
		</form>
	
	</body>
</html>