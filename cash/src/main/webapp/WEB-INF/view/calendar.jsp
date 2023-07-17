<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- JSP 컴파일 시 자바 코드로 변환되는 c: ... (제어 문법) 커스텀 태그  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> <!-- JSTL substring() 호출 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>calendar</title>
	</head>
	<body>
		<c:set var="m" value="${resultMap}"></c:set>
		<h1>${m.targetYear}년 ${m.targetMonth + 1}월</h1>
		<a href="${pageContext.request.contextPath}/calendar?targetYear=${m.targetYear}&targetMonth=${m.targetMonth - 1}" class="btn">이전 달</a>
		<a href="${pageContext.request.contextPath}/calendar?targetYear=${m.targetYear}&targetMonth=${m.targetMonth + 1}" class="float-end btn">다음 달</a>
		
		<table border="1">
			<tr>
				<th>일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
			</tr>
			<tr>
				<c:forEach var="i" begin="0" end="${m.totalTd - 1}" step="1">
				<c:set var="date" value="${i - m.beginBlank + 1}"></c:set>
				
				<c:if test="${i != 0 && i % 7 == 0}"> <!-- 달력 줄바꿈  -->
					</tr><tr>
				</c:if>
				<c:if test="${date < 1}">
					<td>&nbsp;</td>
					
				</c:if>
				
				<c:if test="${!(date < 1 || date > m.lastDate)}">
					<td>
						<div>${date}</div>
						<c:forEach var="c" items="${m.cashbookList}">
							<c:if test="${date == fn:substring(c.cashbookDate, 8, 10)}">
								<div>
									<c:if test="${c.category == '수입'}">
										<span>+${c.price}</span>  <!--c.getPrice() -->
									</c:if>
									<c:if test="${c.category == '지출'}">
										<span style="color: red;">-${c.price}</span>
									</c:if>
								</div>
							</c:if>
						</c:forEach>
					</td>
				</c:if>
				<c:if test="${date > m.lastDate}">
					<%-- <td>${date - lastDate}</td> --%>
					<td>&nbsp;</td>
				</c:if>
				
				</c:forEach>
			</tr>
		
		</table>
		
	</body>
</html>