<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addBoard</title>
	</head>
	<body>
		<h1>게시글 입력</h1>
		
		<form method="post" action="/board/addBoard">
			<table border="1">
				<tr>
					<th>localName</th>
					<td><input type="text" name="localName"></td>
				</tr>
				<tr>
					<th>boardTitle</th>
					<td><input type="text" name="boardTitle"></td>
				</tr>
				<tr>
					<th>boardContent</th>
					<td><textarea cols="50" rows="5" name="boardContent"></textarea></td>
				</tr>
				<tr>
					<th>memberId</th>
					<td><input type="text" name="memberId"></td>
				</tr>
			</table>
			<button type="submit">입력</button>
		</form>
	</body>
</html>