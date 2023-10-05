<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminCSS/personalUserTotalDetailCSS.css">
<title>개인 유저 통합 상세 조회</title>
</head>
<script>
	
	$(document).ready(function () {
		const currentDate = new Date();
		const date7 = currentDate.getDate();
		const date6 = currentDate.getDate() - 1;
		const date5 = currentDate.getDate() - 2;
		const date4 = currentDate.getDate() - 3;
		const date3 = currentDate.getDate() - 4;
		const date2 = currentDate.getDate() - 5;
		const date1 = currentDate.getDate() - 6;
		
		
		$(".day1").text(date1 + "일");
		$(".day2").text(date2 + "일");
		$(".day3").text(date3 + "일");
		$(".day4").text(date4 + "일");
		$(".day5").text(date5 + "일");
		$(".day6").text(date6 + "일");
		$(".day7").text(date7 + "일");
	})
	
	
	
	
</script>
<body>
	<div class="container">
		<h2>${requestScope.userData.username}회원정보</h2>
		<h4 style="margin-bottom: 50px;">개인 정보</h4>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">아이디</span>
				<span class="info-value" id="info-value-userId">${requestScope.userData.userId}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">이름</span>
				<span class="info-value">${requestScope.userData.username}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">생년월일</span>
				<span class="info-value">${requestScope.userData.birth}</span>
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
				<span class="info-key">자기소개</span>
				<span class="info-value" id="info-value-bio">${requestScope.userData.bio}</span>
			</div>
		</div>
		<div class="info-container">
			<div class="info-box">
				<span class="info-key">가입 일자</span>
				<span class="info-value">${requestScope.userData.registration_date}</span>
			</div>
		</div>

		<span id="pagingStatus-span">${requestScope.pageInfo.pagingStatus}</span>
		<table class="table talbe-hover">
			<thead class="table-dark">
				<tr>
					<th>게시물 번호</th>
					<th>등록자</th>
					<th>활동명</th>
					<th>카테고리</th>
					<th>지역</th>
					<th>소요시간</th>
					<th>비용</th>
					<th>활동인원</th>
					<th>평점</th>
					<th>조회수</th>
					<th>등록일자
					<th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.activityData}">
					<tr>
						<td>${bean.activityId}</td>
						<td>${bean.userId}</td>
						<%-- <td>${bean.activityName}</td> --%>
						<td>
							<c:choose>
								<c:when test="${fn:length(bean.activityName) >= 10}">
				                    ${fn:substring(bean.activityName, 0, 10)}..
				                </c:when>
								<c:otherwise>
				                    ${bean.activityName}
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
						<td>${bean.duration}</td>
						<td>${bean.cost}</td>
						<td>${bean.activityNumber}</td>
						<td>${bean.rating}</td>
						<td>${bean.readHit}</td>
						<td>${bean.postedDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="search-container">
			<div class="search-in-container">
				<form name="search-form" action="<%=withFormTag%>" method="get" class="search-form">
					<input type="hidden" name="command" value="memberActivityList">
					<select id="mode" name="mode" class="form-select">
						<option value="all" selected="selected">--- 선택해 주세요 ---
						<option value="activityId">번호
						<option value="userId">등록자
						<option value="activityName">활동명
						<option value="category">카테고리
						<option value="location">지역
						<option value="duration">소요시간
						<option value="cost">비용
						<option value="activityNumber">활동인원
						<option value="rating">평점
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
						10
						<span class="blind"></span>
					</li>
					<li class="item">
						50
						<span class="blind"></span>
					</li>
					<li class="item">
						100
						<span class="blind"></span>
					</li>
					<li class="item">
						500
						<span class="blind"></span>
					</li>
					<li class="item">
						1000
						<span class="blind"></span>
					</li>
					<li class="item">
						5000
						<span class="blind"></span>
					</li>
					<li class="item">
						10000
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
							<span class="blind">data 타입 1</span>
						</span>
					</button>
				</li>
				<li class="item">
					<div class="text_box">
						<strong class="day2"></strong>
					</div>
					<button type="button" class="graph">
						<span class="time data1" style="height: 80%;">
							<span class="blind">data 타입 1</span>
						</span>
					</button>
				</li>
				<li class="item">
					<div class="text_box">
						<strong class="day3"></strong>
					</div>
					<button type="button" class="graph">
						<span class="time data1" style="height: 80%;">
							<span class="blind">data 타입 1</span>
						</span>
					</button>
				</li>
				<li class="item">
					<div class="text_box">
						<strong class="day4"></strong>
					</div>
					<button type="button" class="graph">
						<span class="time data1" style="height: 80%;">
							<span class="blind">data 타입 1</span>
						</span>
					</button>
				</li>
				<li class="item">
					<div class="text_box">
						<strong class="day5"></strong>
					</div>
					<button type="button" class="graph">
						<span class="time data1" style="height: 80%;">
							<span class="blind">data 타입 1</span>
						</span>
					</button>
				</li>
				<li class="item">
					<div class="text_box">
						<strong class="day6"></strong>
					</div>
					<button type="button" class="graph">
						<span class="time data1" style="height: 80%;">
							<span class="blind">data 타입 1</span>
						</span>
					</button>
				</li>
				<li class="item">
					<div class="text_box">
						<strong class="day7"></strong>
					</div>
					<button type="button" class="graph">
						<span class="time data1" style="height: 80%;">
							<span class="blind">data 타입 1</span>
						</span>
					</button>
				</li>
			</ul>
			</div>
			
		</div>
	</div>

</body>
</html>