<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyBoard</title>
	</head>
	<body>
		<h1>게시글 수정</h1>
		<form method="post" action="/board/modifyBoard">
			<input type="hidden" name="boardNo" value="${board.boardNo}">
			<table border="1">
				<tr>
					<th>boardTitle</th>
					<td><input type="text" name="boardTitle" value="${board.boardTitle}"></td>
				</tr>
				<tr>
					<th>boardContent</th>
					<td><textarea cols="50" rows="5" name="boardContent">${board.boardContent}</textarea></td>
				</tr>
				<tr>
					<th>localName</th>
					<td><input type="text" name="localName" value="${board.localName}"></td>
				</tr>
				<tr>
					<th>memberId</th>
					<td><input type="text" name="memberId" value="${board.memberId}"></td>
				</tr>
			</table>
			<button type="submit">수정</button>
		</form>
	</body>
</html>