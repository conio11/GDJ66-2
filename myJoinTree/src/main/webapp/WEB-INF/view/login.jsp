<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login</title>
	</head>
	<body>
<!-- 		<table>
			<tr>
				<td><input type="text" placeholder="사번"></td>
			</tr>
			<tr>
				<td><input type="password" placeholder="Password"></td>
			</tr>
		</table>
		 -->
	<div>
		<a href="/myJoinTree/home">홈</a>
	</div>
	
	    <form action="/myJoinTree/login" method="post">
	        <label for="empNo">Employee Number:</label>
	        <input type="text" id="empNo" name="empNo" value="${loginId}" required><br>
	        
	        <label for="empPw">Password:</label>
	        <input type="password" id="empPw" name="empPw" required><br>
	        
	        <input type="checkbox" id="saveId" name="saveId" value="y">
	        <label for="saveId">Save ID</label><br>
	        
	        <button type="submit">Login</button>
   		</form>
	</body>
</html>