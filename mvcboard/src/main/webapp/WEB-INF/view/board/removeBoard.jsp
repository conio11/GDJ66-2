<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>removeBoard</title>
	</head>
	<body>
		<a href="/board/boardOne?boardNo=${board.boardNo}">이전</a>
		${board}
		<h1>게시글 삭제</h1>
			<form method="post" action="/board/removeBoard">
			<input type="hidden" name="boardNo" value="${board.boardNo}">
			<table border="1">
				<tr>
					<th>boardTitle</th>
					<td>${board.boardTitle}</td>
				</tr>
				<tr>
					<th>boardContent</th>
					<td>${board.boardContent}</td>
				</tr>
				<tr>
					<th>localName</th>
					<td>${board.localName}</td>
				</tr>
				<tr>
					<th>memberId</th>
					<td><input type="text" name="memberId"></td>
				</tr>
			</table>
			<button type="submit">삭제</button>
		</form>
	</body>
</html>