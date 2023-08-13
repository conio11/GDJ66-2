<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<h1>home</h1>
	<div>
		현재 로그인 아이디: ${loginAccount.empNo}
	</div>
	<c:if test="${loginAccount.empNo eq null}">
		<div>
			<a href="/myJoinTree/login">로그인</a>
		</div>
	</c:if>
	<c:if test="${loginAccount.empNo ne null}">
		<div>
			<a href="/myJoinTree/logout">로그아웃</a>
		</div>
	</c:if>
</html>