<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 활동 데이터</title>
<style>
.container {
	margin-top: 130px;
	width: 100%;
	height: 100%;
	max-width: 1460px;
}

.card-img-top {
	width: 100%;
	height: 100%;
}

.card-footer {
	display: flex;
	flex-direction: column;
}

.card {
	height: 370px;
	margin: 0px 10px 10px 10px;
}

img {
	width: 300px;
	height: 180px;
}
.paging-container {
	margin-top: 30px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>회원 활동 데이터</h2>
		<table>
			<thead></thead>
			<tbody>
				<c:set var="colsu" value="4"></c:set>
				<c:forEach var="bean" items="${requestScope.personalActivity}" varStatus="loopStatus">
					<c:if test="${loopStatus.index mod colsu == 0}">
						<tr>
					</c:if>

					<td>
						<div class="card">
							<div class="card-header">${bean.activityName}</div>
							<div class="card-body">
								<a href="#">
									<img alt="${bean.activityName}" src="${pageContext.request.contextPath}/upload/${bean.image}">
								</a>
							</div>
							<div class="card-footer">
								<span>카테고리: ${bean.category}</span>
								<span>주소: ${bean.location}</span>
								상세 주소:
								<c:choose>

									<c:when test="${fn:length(bean.locationDetail) >= 10}">${fn:substring(bean.locationDetail, 0 , 10)}...</c:when>
									<c:otherwise>
                                          ${bean.locationDetail}
                                    </c:otherwise>
								</c:choose>
								<span>조회수: ${bean.readHit}</span>
							</div>
						</div>
					</td>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
</body>
</html>
