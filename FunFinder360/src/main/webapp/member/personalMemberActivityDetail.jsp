<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/activityCSS/personalActivityDetailFormCSS.css">
<title>활동 상세 보기</title>
<script>
	var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
	var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	  return new bootstrap.Tooltip(tooltipTriggerEl)
	})
	function backPage() {
		location.href = "<%=notWithFormTag%>memberActivity"
	}
	var check = false;
	$(document).ready(function() {
		$(".star-icon-fav").click(function() {
			if (!check) {
				$(this).css("color", "#FF9B50");
				check = true;
			} else {
				$(this).css("color", "black");
				check = false;
			}
		})
		$(".heart-icon-fav").click(function() {
			if (!check) {
				$(this).css("color", "#FF6969");
				check = true;
			} else {
				$(this).css("color", "black");
				check = false;
			}
		})
	});
</script>
</head>
<body>
	<div class="container">
		<div class="container-box">
			<span id="category-tag">${requestScope.personalActivityData.category}</span>

			<h2 id="activityName-tag">${requestScope.personalActivityData.activityName}</h2>


			<div class="location-container" style="font-size: 17px;">
				<span>장소 : ${requestScope.personalActivityData.location} </span>
				<span> ${requestScope.personalActivityData.locationDetail}</span>
			</div>
			<div class="cost-container">
				<span>비용 : </span>
				<fmt:formatNumber value="${requestScope.personalActivityData.cost}" pattern="###,###" />
				원
			</div>
			<span id="activityNumber-tag">참가 인원 : ${requestScope.personalActivityData.activityNumber}명</span>
			<div class="rating-container">
				<div class=star-box>
					<c:forEach var="rating" begin="1" end="${requestScope.personalActivityData.rating}">
						<img src="${pageContext.request.contextPath}/upload/star.png" id="star-icon-tag">
					</c:forEach>
				</div>
			</div>
			<div class="userid-postedDate-container" style="font-size: 17px;">
				<span>${requestScope.personalActivityData.userId}</span>
				<span>${requestScope.personalActivityData.postedDate}</span>
				<span id="readHit-teg">조회수 : ${requestScope.personalActivityData.readHit}</span>
			</div>
		</div>
		<c:set var="index" value="0"></c:set>
		<c:set var="contentIndex" value="0"></c:set>
		<c:set var="imgIndex" value="0"></c:set>

		<div class="contents-container">
			<c:forEach var="loop" begin="0" end="${requestScope.personalActivityData.totalRacodeCount - 1}">
				<c:catch var="contentException">
					<c:if test="${not empty requestScope.personalActivityData.contentList}">
						<c:if test="${requestScope.personalActivityData.contentList.get(contentIndex).getOrder() == index}">
							<pre>${requestScope.personalActivityData.contentList.get(contentIndex).getContent()}</pre>
							<c:if test="${requestScope.personalActivityData.contentList.size() > contentIndex }">
								<c:set var="contentIndex" value="${contentIndex + 1}"></c:set>
							</c:if>
						</c:if>
					</c:if>
				</c:catch>
				<c:catch var="imageException">
					<c:if test="${not empty requestScope.personalActivityData.imageList}">
						<c:if test="${requestScope.personalActivityData.imageList.get(imgIndex).getOrder() == index}">
							<img class="contents-image" src="${pageContext.request.contextPath}/upload/${requestScope.personalActivityData.imageList.get(imgIndex).getImage()}">
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
			<div class="bottom-content-container"></div>
		</div>
		<div>
			<button class="back-btn" value="돌아가기" onclick="backPage();">목록</button>
		</div>
	</div>
</body>
</html>