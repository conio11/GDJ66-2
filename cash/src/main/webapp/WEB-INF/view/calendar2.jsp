<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	                   <h5 class="card-title text-center">${targetYear}년 ${targetMonth + 1}월</h5>
          			<div>
          				<a href="${pageContext.request.contextPath}/off/home?targetYear=${targetYear}&targetMonth=${targetMonth - 1}" class="btn btn-outline-secondary">pre</a>
          				<a href="${pageContext.request.contextPath}/off/home?targetYear=${targetYear}&targetMonth=${targetMonth + 1}" class="float-end btn btn-outline-secondary">next</a>
          			</div>
          			<br>
              <!-- Bordered Table -->
              <table class="table table-bordered" style="height: 600px;">
                <thead>
                  <tr class="text-center">
                    <th scope="col" class="text-bg-secondary">일</th>
                    <th scope="col" class="text-bg-secondary">월</th>
                    <th scope="col" class="text-bg-secondary">화</th>
                    <th scope="col" class="text-bg-secondary">수</th>
                    <th scope="col" class="text-bg-secondary">목</th>
                    <th scope="col" class="text-bg-secondary">금</th>
                    <th scope="col" class="text-bg-secondary">토</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <c:forEach var="i" begin="0" end="${totalCell - 1}" step="1">
					<c:set var="d" value="${i - beginBlank + 1}"></c:set> <!-- d = i - beginBlank + 1  -->
				
					<c:if test="${i != 0 && i % 7 == 0}">
						</tr><tr>
					</c:if>

					<c:if test="${d < 1}">
						<td style="color: gray;">${preLastDate + d}</td>
					</c:if>
					<c:if test="${d > lastDate}">
						<td style="color: gray;">${d - lastDate}</td>
					</c:if>
					<c:if test="${!(d < 1 || d > lastDate)}">
						<td>${d}</td>
					</c:if>
				</c:forEach>
                  </tr>
                </tbody>
              </table>
</body>
</html>

