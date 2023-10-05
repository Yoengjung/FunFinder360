<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.container {
	margin-top: 9%;
	width: 100%;
	height: 100%;
}

.container h2 {
	margin-bottom: 65px;
	text-align: center;
}

.info-box {
	width: 830px;
	transform: translateX(-50%);
	left: 50%;
	position: relative;
	display: flex;
	flex-direction: row;
	margin-bottom: 10px;
}

.info-key {
	text-align: center;
	height: 40px;
	line-height: 36px;
	width: 150px;
	border: 1px solid black;
	border-radius: 10px;
	font-size: 18px;
	background-color: #CCCCCC;
}

.info-value {
	height: 40px;
	width: 521px;
	line-height: 36px;
	border: 1px solid black;
	border-radius: 10px;
	margin-left: 10px;
	text-align: center;
	margin-right: 10px;
	background-color: #eeeeee;
}

.backBtn {
	position: relative;
	left: 50%;
	transform: translateX(-50%);
	margin-top: 30px;
}

.info-value-change-input {
	height: 40px;
	width: 521px;
	line-height: 36px;
	border: 1px solid black;
	border-radius: 10px;
	margin-left: 10px;
	text-align: center;
	margin-right: 10px;
	display: none;
}

.info-value-password {
	height: 40px;
	width: 521px;
	line-height: 36px;
	border: 1px solid black;
	border-radius: 10px;
	margin-left: 10px;
	text-align: center;
	margin-right: 10px;
}

.info-value-change-input-businessName {
	height: 40px;
	width: 521px;
	line-height: 36px;
	border: 1px solid black;
	border-radius: 10px;
	margin-left: 10px;
	text-align: center;
	margin-right: 10px;
}

#pagingStatus-span {
	position: relative;
	display: block;
	float: right;
	font-size: 20px;
	margin-bottom: 10px;
	border: 2px solid black;
	border-radius: 10px;
	width: auto;
	padding: 0px 10px;
}

.container h4 {
	text-align: center;
	margin-bottom: 10px;
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

.paging-container {
	margin-top: 20px;
}

.input-group {
	width: 300px;
}

.form-select {
	width: 200px;
}
</style>
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


</body>
</html>