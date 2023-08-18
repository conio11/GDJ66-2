<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>localNameList</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script> <!--jQuery CDN  -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>	<!-- js 기반 차트 라이브러리 -->
		<script>
			$(document).ready(function() {
				// document 코드보다 아래에 위치, ajax 보다 위에
				const x = [];
				const y = [];
				
				// 동기 호출로 x, y값 세팅
				$.ajax({
					async : false, // true(비동기: 기본값), false(동기)	
					url: '/rest/localNameList', // get 요청을 보낼 주소
					type: 'get',
					success: function(model) { // ajax 요청이 성공했을 때 호출되는 콜백 함수 -> 서버로부터 받아온 데이터가 model 매개변수로 전달 // model: list
						// Backend Model -> Frontend 모델로 변경
						// model -> {'model':[{localName: '부산', cnt: 10}, 
						//            			{localName: '서울', cnt: 20}, ..]} (객체의 배열)
						model.forEach(function(item, index) { 
							$('#target').append('<tr>');
							$('#target').append('<td>'+ item.localName + '</td>')
							$('#target').append('<td>'+ item.cnt + '</td>')
							$('#target').append('</tr>');
							
							// chart 모델 생성
							x.push(item.localName);
							y.push(item.cnt);
							
							// $('#target').html(html); // .html(): 내부 html 컨텐츠 반환	
						});
						// let html = ''; // AJAX API 결과물(자료구조) 출력 
						
					}
				
				});
			/* 		`<tr>
						<td>GDJ66</td>
						<td>25</td>
					</tr>`; */
				new Chart("target2", {
					  type: "bar",
					  data: {
					    labels: x,
					    datasets: [{
					      // backgroundColor: barColors, // 디폴트 색상 사용
					      data: y
					    }]
					  }
					  // options: {...}
					});
			});
			
			// const barColors = ["red", "green","blue","orange","brown"]; // 디폴트 색상 사용

		
		</script>
	</head>
	<body>
		<h1>Ajax API 사용으로 localNameList 목록 가져오기</h1>
		
		<table border="1">
			<thead>
				<tr>
					<th>지역명</th>
					<th>게시글 수</th>
				</tr>
			</thead>
			<tbody id="target">
			</tbody>
		</table>
		<canvas id="target2" style="width:100%; max-width:700px"></canvas>	
	</body>
</html>