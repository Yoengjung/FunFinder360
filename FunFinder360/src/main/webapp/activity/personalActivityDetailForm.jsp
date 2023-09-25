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
		${requestScope.personalActivityData.contentList}
		${requestScope.personalActivityData.imageList}
		
		<c:forEach var="index" begin="0" end="${requestScope.personalActivityData.totalRacodeCount - 1}">
			<c:catch var="contentException">
				<c:if test="${not empty requestScope.personalActivityData.contentList and requestScope.personalActivityData.contentList.size() > index}">
					<c:if test="${requestScope.personalActivityData.contentList.get(index).getOrder() == index}">
                ${requestScope.personalActivityData.contentList.get(index).getContent()}
            </c:if>
				</c:if>
			</c:catch>

			<c:catch var="imageException">
				<c:if test="${not empty requestScope.personalActivityData.imageList and requestScope.personalActivityData.imageList.size() > index}">
					<c:if test="${requestScope.personalActivityData.imageList.get(index).getOrder() == index}">
                ${requestScope.personalActivityData.imageList.get(index).getImage()}
            </c:if>
				</c:if>
			</c:catch>

			<!-- 예외 처리 코드 -->
			<c:if test="${not empty contentException}">
				
        예외가 발생했습니다.
    </c:if>

			<c:if test="${not empty imageException}">

        예외가 발생했습니다.
    </c:if>
		</c:forEach>


	</div>
</body>
</html>