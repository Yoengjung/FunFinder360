<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminCSS/personalUserTotalDetailCSS.css">
<meta charset="UTF-8">
<title>기업 수익 모델</title>

<style>
.container {
	margin-top: 130px;
	width: 100%;
	height: 100%;
}

.container h2 {
	margin-bottom: 30px;
}

.table-head-box {
	text-align: center;
}

.table-body-box {
	text-align: center;
}

.no-head {
	width: 15%;
}

.title-head {
	text-align: left;
	width: 15%; /* 50% -> 20%로 수정함 */
}

.registrant-head {
	width: 15%;
}

.posted-date-head {
	width: 15%;
}

.readhit-head {
	width: 10%;
}
.event-head {
	width: 15% 
}
.date-head {
	width: 25%
}

.title-box {
	text-align: left;
}

.title-box a {
	color: #3366FF;
}

.title-box a:hover {
	text-decoration: underline;
}

.input-group {
	width: 300px;
}

.form-select {
	width: 200px;
}

.search-form {
	display: flex;
	flex-direction: row;
	justify-content: center;
}

.keyword-input-box {
	margin: 0 20px;
}
</style>
<script type="text/javascript">
$(document).ready(
	function() {
		var questionOptionList = $('#mode option');
		for (var i = 0; i < questionOptionList.length; i++) {
			if (questionOptionList[i].value == '${requestScope.pageInfo.mode}') {
				questionOptionList[i].selected = true;
			}
		}
		$('#keyowrd').val('${requestScope.pageInfo.keyword}');

		$("#mode").change(function() {
			if ($(this).val() != 'all') {
				$('#keyword').attr('disabled', false);
			} else {
				$('#keyword').val('');
				$('#keyword').attr('disabled', true);
			}
		});
	});
	
$(document).ready(function() {
	const currentDate = new Date();

	// 각 날짜 계산
	const date7 = new Date(currentDate);
	date7.setDate(currentDate.getDate() - 1);
	const month7 = date7.getMonth() + 1;
	const day7 = date7.getDate();

	const date6 = new Date(currentDate);
	date6.setDate(currentDate.getDate() - 2); // 전전날
	const month6 = date6.getMonth() + 1;
	const day6 = date6.getDate();

	const date5 = new Date(currentDate);
	date5.setDate(currentDate.getDate() - 3);
	const month5 = date5.getMonth() + 1;
	const day5 = date5.getDate();

	const date4 = new Date(currentDate);
	date4.setDate(currentDate.getDate() - 4);
	const month4 = date4.getMonth() + 1;
	const day4 = date4.getDate();

	const date3 = new Date(currentDate);
	date3.setDate(currentDate.getDate() - 5);
	const month3 = date3.getMonth() + 1;
	const day3 = date3.getDate();

	const date2 = new Date(currentDate);
	date2.setDate(currentDate.getDate() - 6);
	const month2 = date2.getMonth() + 1;
	const day2 = date2.getDate();

	const date1 = new Date(currentDate);
	date1.setDate(currentDate.getDate() - 7); // 일주일 전
	const month1 = date1.getMonth() + 1;
	const day1 = date1.getDate();

	// 각 클래스에 텍스트 설정
	$(".day1").text(`\${month1}월 \${day1}일`);
	$(".day2").text(`\${month2}월 \${day2}일`);
	$(".day3").text(`\${month3}월 \${day3}일`);
	$(".day4").text(`\${month4}월 \${day4}일`);
	$(".day5").text(`\${month5}월 \${day5}일`);
	$(".day6").text(`\${month6}월 \${day6}일`);
	$(".day7").text(`\${month7}월 \${day7}일`);

	
	const graphData = [
	    ${requestScope.dateReadHitCount[0].readHit},
	    ${requestScope.dateReadHitCount[1].readHit},
	    ${requestScope.dateReadHitCount[2].readHit},
	    ${requestScope.dateReadHitCount[3].readHit},
	    ${requestScope.dateReadHitCount[4].readHit},
	    ${requestScope.dateReadHitCount[5].readHit},
	    ${requestScope.dateReadHitCount[6].readHit}
	];

	// 각 그래프에 대해 높이를 동적으로 설정합니다.
	$('.graph').each(function (index) {
	    const percentage = (graphData[index] / 1000) * 100;
	    const heightPercentage = percentage + '%';
	    $(this).find('.time').css('height', heightPercentage);
	});
})
</script>
</head>
<body>
	<div class="container">
		<h2>기업 수익 모델</h2>
		<div>
			<h4 style="margin-top: 50px;">총계</h4>
			<div class="total-container">
				<div class="totalReadHit-container">
					<div class="totalReadHit-box">
						<span>총 조회수</span> 
						<span>${requestScope.readHitTotalCount}</span>
					</div>
				</div>
				<div class='totalReviewCount-container'>
					<div class="totalReivewCount-box">
						<span>총 댓글수</span> 
						<span>${requestScope.reviewTotalCount}</span>
					</div>
				</div>
			</div>
		</div>
		<div class='readHit-chart-container'>
			<div class="vertical_chart_box">
				<ul class="axis_y">
					<li class="item">100 <span class="blind"></span>
					</li>
					<li class="item">200 <span class="blind"></span>
					</li>
					<li class="item">300 <span class="blind"></span>
					</li>
					<li class="item">400 <span class="blind"></span>
					</li>
					<li class="item">500 <span class="blind"></span>
					</li>
					<li class="item">600 <span class="blind"></span>
					</li>
					<li class="item">700 <span class="blind"></span>
					</li>
					<li class="item">800 <span class="blind"></span>
					</li>
					<li class="item">900 <span class="blind"></span>
					</li>
					<li class="item">1000 <span class="blind"></span>
					</li>

				</ul>
				<ul class="axis_x">
					<!-- item 을 차트에서 표현해야하는 개수만큼 나열한다. -->
					<li class="item">
						<div class="text_box">
							<strong class="day1" id="day1"></strong>
						</div>
						<button type="button" class="graph">
							<span class="time data1" style="height: 80%;"> <span
								class="blind">${requestScope.dateReadHitCount[0].readHit}</span>
							</span>
						</button>

					</li>
					<li class="item">
						<div class="text_box">
							<strong class="day2"></strong>
						</div>
						<button type="button" class="graph">
							<span class="time data1" style="height: 80%;"> <span
								class="blind">${requestScope.dateReadHitCount[1].readHit}</span>
							</span>
						</button>
					</li>
					<li class="item">
						<div class="text_box">
							<strong class="day3"></strong>
						</div>
						<button type="button" class="graph">
							<span class="time data1" style="height: 80%;"> <span
								class="blind">${requestScope.dateReadHitCount[2].readHit}</span>
							</span>
						</button>
					</li>
					<li class="item">
						<div class="text_box">
							<strong class="day4"></strong>
						</div>
						<button type="button" class="graph">
							<span class="time data1" style="height: 80%;"> <span
								class="blind">${requestScope.dateReadHitCount[3].readHit}</span>
							</span>
						</button>
					</li>
					<li class="item">
						<div class="text_box">
							<strong class="day5"></strong>
						</div>
						<button type="button" class="graph">
							<span class="time data1" style="height: 80%;"> <span
								class="blind">${requestScope.dateReadHitCount[4].readHit}</span>
							</span>
						</button>
					</li>
					<li class="item">
						<div class="text_box">
							<strong class="day6"></strong>
						</div>
						<button type="button" class="graph">
							<span class="time data1" style="height: 80%;"> <span
								class="blind">${requestScope.dateReadHitCount[5].readHit}</span>
							</span>
						</button>
					</li>
					<li class="item">
						<div class="text_box">
							<strong class="day7"></strong>
						</div>
						<button type="button" class="graph">
							<span class="time data1" style="height: 80%;"> <span
								class="blind">${requestScope.dateReadHitCount[6].readHit}</span>
							</span>
						</button>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>