<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>boardOne</title>
	</head>
	<body>
		<a href="/board/boardList">이전</a>
		<h1>상세정보</h1>
		
		<table border="1">
			<tr>
				<th>boardNo</th>
				<td>${board.boardNo}</td>
			</tr>
			<tr>
				<th>localName</th>
				<td>${board.localName}</td>
			</tr>
			<tr>
				<th>boardTitle</th>
				<td>${board.boardTitle}</td>
			</tr>
			<tr>
				<th>boardContent</th>
				<td>${board.boardContent}</td>
			</tr>
			<tr>
				<th>memberId</th>
				<td>${board.memberId}</td>
			</tr>
			<tr>
				<th>createdate</th>
				<td>${board.createdate}</td>
			</tr>
			<tr>
				<th>updatedate</th>
				<td>${board.updatedate}</td>
			</tr>
			<tr>
				<th>boardfiles</th>
				<td>
					<c:forEach var="f" items="${boardfiles}">
					<a href="/upload/${f.saveFilename}">${f.originFilename}</a><br>
					</c:forEach>
				</td>
			</tr>
		</table>
		<a href="/board/modifyBoard?boardNo=${board.boardNo}">수정</a>
		<a href="/board/removeBoard?boardNo=${board.boardNo}">삭제</a>
	
	</body>
</html>