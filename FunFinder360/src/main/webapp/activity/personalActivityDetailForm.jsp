<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/activityCSS/personalActivityDetailFormCSS.css" type="text/css">

<title>활동 상세 보기</title>
</head>
<body>
	<div class="container">
		<h2>상세보기</h2>
		<div>
			<span>${requestScope.personalActivityData.userId}</span>
			<span>${requestScope.personalActivityData.activityName}</span>
			<span>${requestScope.personalActivityData.category}</span>
			<span>${requestScope.personalActivityData.location}</span>
			<span>${requestScope.personalActivityData.locationDetail}</span>
			<span>${requestScope.personalActivityData.cost}</span>
			<span>${requestScope.personalActivityData.activityNumber}</span>
			<span>${requestScope.personalActivityData.rating}</span>
			<span>${requestScope.personalActivityData.readHit}</span>
			<span>${requestScope.personalActivityData.postedDate}</span>

		</div>
		<c:set var="index" value="0"></c:set>
		<c:set var="contentIndex" value="0"></c:set>
		<c:set var="imgIndex" value="0"></c:set>

		<c:forEach var="loop" begin="0" end="${requestScope.personalActivityData.totalRacodeCount - 1}">
			<c:catch var="contentException">
				<c:if test="${not empty requestScope.personalActivityData.contentList}">
					<c:if test="${requestScope.personalActivityData.contentList.get(contentIndex).getOrder() == index}">
					${requestScope.personalActivityData.contentList.get(contentIndex).getContent()}
					<c:if test="${requestScope.personalActivityData.contentList.size() > contentIndex }">
							<c:set var="contentIndex" value="${contentIndex + 1}"></c:set>
						</c:if>
					</c:if>
				</c:if>
			</c:catch>
			<c:catch var="imageException">
				<c:if test="${not empty requestScope.personalActivityData.imageList}">
					<c:if test="${requestScope.personalActivityData.imageList.get(imgIndex).getOrder() == index}">
						<img src="${pageContext.request.contextPath}/upload/${requestScope.personalActivityData.imageList.get(imgIndex).getImage()}">
						<c:if test="${requestScope.personalActivityData.imageList.size() > imgIndex }">
							<c:set var="imgIndex" value="${imgIndex + 1}"></c:set>
						</c:if>

					</c:if>
				</c:if>
			</c:catch>
			<c:if test="${not empty contentException}"></c:if>
			<c:if test="${not empty imageException}"></c:if>
			<c:set var="index" value="${index + 1}"></c:set>
		</c:forEach>
	</div>
</body>
</html>