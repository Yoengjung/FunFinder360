<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminCSS/personalUserTotalDetailCSS.css">
<meta charset="UTF-8">
<title>개인 활동</title>

<style>
.container1 {
	width: 100%;
	height: 100vh;
	min-height: 100vh;
}

.container2 {
	width: 80%;
	position: relative;
	left: 50%;
	transform: translateX(-50%);
	top: 25%;
}

.container1 h2 {
	margin-bottom: 30px;
	text-align: center;
}

.table-head-box {
	text-align: center;
}

.table-body-box {
	text-align: center;
}

.no-head {
	width: 20%;
}

.title-head {
	text-align: left;
	width: 15%; /* 50% -> 20%로 수정함 */
}

.registrant-head {
	width: 20%;
}

.posted-date-head {
	width: 20%;
}

.readhit-head {
	width: 10%;
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
	
function button_event(event) {
	  event.preventDefault();
	  const activityId = $("#activityId-tag").val();
	  if (confirm("정말 삭제하시겠습니까??")) {
	    var url = "<%=notWithFormTag%>personalDeleteActivity&activityId=" + activityId;
	    var xhr = new XMLHttpRequest();

	    xhr.open("GET", url, true);

	    xhr.onload = function () {
	      if (xhr.status === 200) {
	        window.location.reload();
	      } else {
	        console.error("요청 실패");
	      }
	    };

	    xhr.send();
	  } else {
	    return;
	  }
	}

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
	<div class="container1">
		<div class="container2">
			<h2>나의 활동</h2>
			<table class="table">
				<thead class="table-dark">
					<tr>
						<th class="table-head-box no-head">활동 제목</th>
						<th class="table-head-box title-head">카테고리</th>
						<th class="table-head-box registrant-head">위치</th>
						<th class="table-head-box posted-date-head">상세주소</th>
						<th class="table-head-box readhit-head" style="width: 70px;">조회수</th>
						<th class="table-head-box">등록일</th>
						<th class="table-head-box">옵션</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="personalActivity" items="${requestScope.personalActivityList}">
						<input type="hidden" value="${personalActivity.activityId}" id="activityId-tag">
						<tr>
							<td class="table-body-box">
								<c:choose>
									<c:when test="${fn:length(personalActivity.activityName) >= 10}">
										<a href="<%=notWithFormTag%>personalMemberActivityDetail&activityId=${personalActivity.activityId}">${fn:substring(personalActivity.activityName, 0, 10)}... </a>
									</c:when>
									<c:otherwise>
										<a href="<%=notWithFormTag%>personalMemberActivityDetail&activityId=${personalActivity.activityId}">${personalActivity.activityName} </a>
									</c:otherwise>
									<%-- ${personalActivity.activityName} --%>
								</c:choose>
							</td>
							<td class="table-body-box title-box">${personalActivity.category}</td>
							<td class="table-body-box">${personalActivity.location}</td>
							<td class="table-body-box">${personalActivity.locationDetail}</td>
							<td class="table-body-box">${personalActivity.readHit}</td>
							<td class="table-body-box">${personalActivity.postedDate}</td>
							<td class="table-body-box">
								<button  type="button" onclick="button_event(event);">삭제</button>
								<%-- <a href="<%=notWithFormTag%>personalDeleteActivity&activityId=${personalActivity.activityId}" onclick="return button_event()">삭제</a> --%>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="search-container">
				<div class="search-in-container">
					<form name="search-form" action="<%=withFormTag%>" method="get" class="search-form">
						<input type="hidden" name="command" value="memberActivity">
						<select id="mode" name="mode" class="form-select">
							<option value="all" selected="selected">--- 선택해 주세요 ---
							<option value="activityName">활동 제목
							<option value="category">카테고리
							<option value="location">위치
							<option value="locationDetail">상세주소
							<option value="readHit">조회수
							<option value="postedDate">등록일
						</select>
						<div class="input-group">
							<input class="keyword-input-box form-control" type="text" name="keyword" id="keyword" placeholder="키워드 입력" autocomplete="off">
						</div>
						<button type="submit" class="btn btn-default form-control-sm search-btn" onclick="">검색</button>
					</form>
				</div>
			</div>
			<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
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
						<li class="item">
							100
							<span class="blind"></span>
						</li>
						<li class="item">
							200
							<span class="blind"></span>
						</li>
						<li class="item">
							300
							<span class="blind"></span>
						</li>
						<li class="item">
							400
							<span class="blind"></span>
						</li>
						<li class="item">
							500
							<span class="blind"></span>
						</li>
						<li class="item">
							600
							<span class="blind"></span>
						</li>
						<li class="item">
							700
							<span class="blind"></span>
						</li>
						<li class="item">
							800
							<span class="blind"></span>
						</li>
						<li class="item">
							900
							<span class="blind"></span>
						</li>
						<li class="item">
							1000
							<span class="blind"></span>
						</li>

					</ul>
					<ul class="axis_x">
						<!-- item 을 차트에서 표현해야하는 개수만큼 나열한다. -->
						<li class="item">
							<div class="text_box">
								<strong class="day1" id="day1"></strong>
							</div>
							<button type="button" class="graph">
								<span class="time data1" style="height: 80%;">
									<span class="blind">${requestScope.dateReadHitCount[0].readHit}</span>
								</span>
							</button>

						</li>
						<li class="item">
							<div class="text_box">
								<strong class="day2"></strong>
							</div>
							<button type="button" class="graph">
								<span class="time data1" style="height: 80%;">
									<span class="blind">${requestScope.dateReadHitCount[1].readHit}</span>
								</span>
							</button>
						</li>
						<li class="item">
							<div class="text_box">
								<strong class="day3"></strong>
							</div>
							<button type="button" class="graph">
								<span class="time data1" style="height: 80%;">
									<span class="blind">${requestScope.dateReadHitCount[2].readHit}</span>
								</span>
							</button>
						</li>
						<li class="item">
							<div class="text_box">
								<strong class="day4"></strong>
							</div>
							<button type="button" class="graph">
								<span class="time data1" style="height: 80%;">
									<span class="blind">${requestScope.dateReadHitCount[3].readHit}</span>
								</span>
							</button>
						</li>
						<li class="item">
							<div class="text_box">
								<strong class="day5"></strong>
							</div>
							<button type="button" class="graph">
								<span class="time data1" style="height: 80%;">
									<span class="blind">${requestScope.dateReadHitCount[4].readHit}</span>
								</span>
							</button>
						</li>
						<li class="item">
							<div class="text_box">
								<strong class="day6"></strong>
							</div>
							<button type="button" class="graph">
								<span class="time data1" style="height: 80%;">
									<span class="blind">${requestScope.dateReadHitCount[5].readHit}</span>
								</span>
							</button>
						</li>
						<li class="item">
							<div class="text_box">
								<strong class="day7"></strong>
							</div>
							<button type="button" class="graph">
								<span class="time data1" style="height: 80%;">
									<span class="blind">${requestScope.dateReadHitCount[6].readHit}</span>
								</span>
							</button>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>