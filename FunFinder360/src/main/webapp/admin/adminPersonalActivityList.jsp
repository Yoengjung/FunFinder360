<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업주 회원 정보 관리</title>
<style>
.container {
	margin-top: 9%;
	width: 100%;
	height: 100%;
	max-width: 1460px;
}

.container h2 {
	text-align: center;
	margin-bottom: 40px;
}

#pagingStatus-span {
	position: relative;
	display: block;
	float: right;
	font-size: 20px;
	margin-bottom: 10px;
	border: 2px solid black;
	border-radius: 10px;
	width: 132px;
	padding: 0px 10px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>업주 게시물 리스트</h2>
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
					<th>이벤트<th>
					<th>조회수</th>
					<th>등록일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.dataList}">
					<tr>
						<td>${bean.activityId}</td>
						<td>${bean.userid}</td>
						<td>${bean.activitiyName}</td>
						<td>${bean.category}</td>
						<td>${bean.location} ${bean.locationDetail}</td>
						<td align="center">${bean.price}</td>
						<td align="center">${bean.activitiyNumber}</td>
						<td>${bean.openTime} ~ ${bean.closeTime}</td>
						<c:if test="${not empty bean.event}">
							<td align="center">o</td>
						</c:if>
						<c:if test="${empty bean.event}">
							<td align="center">x</td>
						</c:if>
						<td align="center">${bean.readHit}</td>
						<td>${bean.postedDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
</body>
</html>