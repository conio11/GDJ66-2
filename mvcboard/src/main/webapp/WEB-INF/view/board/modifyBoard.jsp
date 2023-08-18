<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyBoard</title>
	</head>
	<body>
		<a href="/board/boardOne?boardNo=${board.boardNo}">이전</a>
		<h1>게시글 수정</h1>
		<form method="post" action="/board/modifyBoard" enctype="multipart/form-data">
			<input type="hidden" name="boardNo" value="${board.boardNo}">
			<table border="1">
				<tr>
					<th>localName</th>
					<td><input type="text" name="localName" value="${board.localName}"></td>
				</tr>
				<tr>
					<th>boardTitle</th>
					<td><input type="text" name="boardTitle" value="${board.boardTitle}"></td>
				</tr>
				<tr>
					<th>boardContent</th>
					<td><textarea cols="50" rows="5" name="boardContent">${board.boardContent}</textarea></td>
				</tr>
				
				<tr>
					<th>memberId</th>
					<td><input type="text" name="memberId" value="${board.memberId}" readonly="readonly"></td>
				</tr>
				<tr>
					<th>boardfiles</th>
					<td><input type="file" name="multipartFile" multiple="multiple"></td> <!-- 여러 개의 파일 동시에 선택 가능  -->
				</tr>
			</table>
			<button type="submit">수정</button>
		</form>
	</body>
</html>