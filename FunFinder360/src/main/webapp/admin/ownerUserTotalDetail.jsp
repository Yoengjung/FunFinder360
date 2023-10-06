<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminCSS/personalUserTotalDetailCSS.css">
<title>업주 유저 통합 상세 조회</title>
</head>
<style>

</style>
<script>
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

<body>
	<div class="container">
		<h2>${requestScope.userData.userName}회원정보</h2>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">아이디</span>
				<span class="info-value" id="info-value-userId">${requestScope.userData.userId}</span>
				<input type="hidden" id="info-value-userId-hidden" value="${requestScope.userData.userId}">
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">이름</span>
				<span class="info-value">${requestScope.userData.userName}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">사업이름</span>
				<span class="info-value" id="info-value-businessName">${requestScope.userData.businessName}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">사업 구분</span>
				<span class="info-value">${requestScope.userData.businessType}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">사업자 등록번호</span>
				<span class="info-value">${requestScope.userData.businessNumber}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">전화번호</span>
				<span class="info-value" id="info-value-phone">${requestScope.userData.phoneNumber}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">이메일</span>
				<span class="info-value" id="info-value-email">${requestScope.userData.email}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">사업소개</span>
				<span class="info-value" id="info-value-bio">${requestScope.userData.bio}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">가입 일자</span>
				<span class="info-value">${requestScope.userData.registrationDate}</span>
			</div>
		</div>
		<h4 style="margin-top: 40px">업주 게시물 리스트</h4>
		<span id="pagingStatus-span">${requestScope.pageInfo.pagingStatus}</span>
		<table class="table talbe-hover">
			<thead class="table-dark">
				<tr>
					<th>번호</th>
					<th>등록자</th>
					<th>활동명</th>
					<th>카테고리</th>
					<th>지역</th>
					<th>가격</th>
					<th>인원</th>
					<th>영업시간</th>
					<th>이벤트</th>
					<th>조회수</th>
					<th>등록일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.activityData}">
					<tr>
						<td>${bean.activityId}</td>
						<td>${bean.userid}</td>
						<td>
							<c:choose>
								<c:when test="${fn:length(bean.activitiyName) >= 10}">
				                    ${fn:substring(bean.activitiyName, 0, 10)}..
				                </c:when>
								<c:otherwise>
				                    ${bean.activitiyName}
								</c:otherwise>
							</c:choose>
						</td>

						<td>${bean.category}</td>
						<td>
							<c:choose>
								<c:when test="${fn:length(bean.location) + fn:length(bean.locationDetail) > 15}">
						        	${bean.location} ${fn:substring(bean.locationDetail, 0, 15 - fn:length(bean.location))}...
						        </c:when>
								<c:otherwise>
						            ${bean.location} ${bean.locationDetail}
						        </c:otherwise>
							</c:choose>
						</td>
						<td align="center">${bean.price}</td>
						<td align="center">${bean.activitiyNumber}</td>
						<td>${bean.openTime}~${bean.closeTime}</td>
						<td>
							<c:choose>
								<c:when test="${fn:length(bean.event) >= 10}">
					                    ${fn:substring(bean.event, 0, 10)}..
					                </c:when>
								<c:otherwise>
					                    ${bean.event}
									</c:otherwise>
							</c:choose>
						</td>
						<td align="center">${bean.readHit}</td>
						<td>${bean.postedDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="search-container">
			<div class="search-in-container">
				<form name="search-form" action="<%=withFormTag%>" method="get" class="search-form">
					<input type="hidden" name="command" value="ownerMemberActivityList">
					<select id="mode" name="mode" class="form-select">
						<option value="all" selected="selected">--- 선택해 주세요 ---
						<option value="activityId">번호
						<option value="userId">등록자
						<option value="activityName">활동명
						<option value="category">카테고리
						<option value="location">지역
						<option value="openTime">영업시작
						<option value="closeTime">영업종료
						<option value="event">이벤트
						<option value="readHit">조회수
						<option value="postedDate">등록일자
					</select>
					<div class="input-group">
						<input class="keyword-input-box form-control" type="text" name="keyword" id="keyword" placeholder="키워드 입력" autocomplete="off">
					</div>
					<button type="submit" class="btn btn-success form-control-sm search-btn" onclick="">검색</button>
				</form>
			</div>
		</div>
		<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>

		<div>
			<h4 style="margin-top: 50px;">총계</h4>
			<div class="total-container">
				<div class="totalReadHit-container">
					<div class="totalReadHit-box">
						<span>총 조회수</span> <span>${requestScope.readHitTotalCount}</span>
					</div>
				</div>
				<div class='totalReviewCount-container'>
					<div class="totalReivewCount-box">
						<span>총 댓글수</span> <span>${requestScope.reviewTotalCount}</span>
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