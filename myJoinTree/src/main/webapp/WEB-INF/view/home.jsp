<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>home</title>
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
		<h1>home</h1>
		<c:if test="${loginAccount.empNo eq null}">
			<div>
				<a href="/myJoinTree/login/login">로그인</a>
			</div>
		</c:if>
		<c:if test="${loginAccount.empNo ne null}">
			<div>
				현재 사용자 : ${empName}
				<%-- 현재 로그인 아이디: ${loginAccount.empNo} --%>
			</div>
			<div>
				<a href="/myJoinTree/empInfo/empInfo">나의 정보</a>
			</div>
			<div>
				<a href="/myJoinTree/community/freeCommList">자유 게시판</a>
			</div>
			<div>
				<a href="/myJoinTree/community/anonymousCommList">익명 게시판</a>
			</div>
			<div>
				<a href="/myJoinTree/community/secondhandCommList">중고장터 게시판</a>
			</div>
			<div>
				<a href="/myJoinTree/community/lifeEventCommList">경조사 게시판</a>
			</div>
			<div>
				<a href="/myJoinTree/logout">로그아웃</a>
			</div>
		</c:if>

	</body>
</html>