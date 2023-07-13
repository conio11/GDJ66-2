<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addBoard</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$("#addBtn").click(function() {
					if ($(".file").last().val() == '') { // file 클래스의 마지막 요소가 비어 있으면
						alert("빈 파일 업로드 태그가 있습니다.");
					} else {
						let inputName = "multipartFile";
						$("#addFile").append("<div><input type=\"file\" name=" + inputName + " class=\"file\"></div>");
					}
				});
				
				$("#removeBtn").click(function() {
					if ($(".file").last.val() != 1) {
						$(".file").last().remove(); // file 클래스의 마지막 요소 삭제
					} else {
						alert("빈 파일 업로드 태그가 없습니다.");
					}
				});
			});
		</script>
	</head>
	<body>
		<a href="/board/boardList">이전</a>
		<h1>게시글 입력</h1>
		
		<form method="post" action="/board/addBoard" enctype="multipart/form-data"> <!-- multipart 인코딩 타입 지정하지 않으면 url로 저장 주의  -->
			<table border="1">
				<tr>
					<th>localName</th>
					<td><input type="text" name="localName"></td>
				</tr>
				<tr>
<%-- 					<th>localName</th>
					<td>
						<select name="localName">
							<option>-- 선택 --</option>
							<c:forEach var="b" items="${localNameList}">
								  <option>${b.localName}</option>
							</c:forEach>
						</select>
					</td>
			    </tr> --%>
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
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="multipartFile" class="file">
						<div id="addFile"></div>
					</td>
					
				</tr>
			</table>
			
			<button type="button" id="addBtn">파일 추가</button>
			<button type="button" id="removeBtn">파일 제거</button>
			<button type="submit">입력</button>
		</form>
	</body>
</html>