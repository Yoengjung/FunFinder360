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
		<h2>개인 게시물 리스트</h2>
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
					<th>등록일자<th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.dataList}">
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
						             ${bean.location} ${fn:substring(bean.locationDetail, 0, fn:length(bean.location) - 15)}...
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
	</div>
	<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
</body>
</html>