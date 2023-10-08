<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/activityCSS/personalCultureActivitesListFormCSS.css">
<title>회원 활동 데이터</title>
<script type="text/javascript">
	$(document)
			.ready(
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
</script>
</head>
<body>
	<div class="container1">
		<div class="container2">
			<div class="img-box">
				<img
					src="${pageContext.request.contextPath}/common/image/1991070551-huge.jpg">
				<span>문화 - 엔터테인먼트</span>
			</div>
		</div>
		<div class="container3">
			<div class="nav-bar-container">
				<ul class="middle-nav-bar">
					<li><a href="<%=notWithFormTag%>cultureActivitesList">문화 - 엔터테인먼트</a></li>
					<li><a href="<%=notWithFormTag%>foodActivitesList">음식 - 요리</a></li>
					<li><a href="<%=notWithFormTag%>studyActivitesList">교육 - 학습</a></li>
					<li><a href="#">여행 - 모험</a></li>
					<li><a href="#">게임 - 취미</a></li>
				</ul>
			</div>
			<div class="activity-container">
				<c:forEach var="bean" items="${requestScope.personalActivity}">
					<div class="activity-item">
						<div class="activity-img">
							<img class="img-sizing"
								src="${pageContext.request.contextPath}/upload/${bean.image}">
						</div>
						<div class="activity-contents">
							<h5>${bean.activityName}</h5>
							<c:choose>
									<c:when test="${fn:length(bean.content) >= 200}"><p>${fn:substring(bean.content, 0 , 200)}...</p></c:when>
									<c:otherwise>
	                                          <p>${bean.content}</p>
	                                    </c:otherwise>
								</c:choose>
							<p>장소 : ${bean.location} ${bean.locationDetail}
							<p>게시자 : ${bean.userId}
							<p>조회수 : ${bean.readHit}
							<p>게시 시간 : ${bean.postedDate}
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
		</div>
		
	</div>
	
	


	<%-- <form name="search-form" action="<%=withFormTag%>" method="get"
				class="search-form">
				<input type="hidden" name="command" value="activitesList"> <select
					id="mode" name="mode" class="form-select">
					<option value="readhit">조회수
					<option value="postedDate">최신순
				</select>
				<button type="submit"
					class="btn btn-success form-control-sm search-btn" onclick="">검색</button>
			</form>





			<div class="search-container">
				<div class="search-in-container">
					<form name="search-form" action="<%=withFormTag%>" method="get"
						class="search-form">
						<input type="hidden" name="command" value="activitesList">
						<select id="mode" name="mode" class="form-select">
							<option value="all" selected="selected">--- 선택해 주세요 ---


							
							<option value="activityName">활동 제목
							<option value="userid">등록자
							<option value="category">카테고리
							<option value="location">주소
							<option value="locationdetail">상세주소
						</select>
						<div class="input-group">
							<input class="keyword-input-box form-control" type="text"
								name="keyword" id="keyword" placeholder="키워드 입력"
								autocomplete="off">
						</div>
						<button type="submit"
							class="btn btn-success form-control-sm search-btn" onclick="">검색</button>
					</form>
				</div>
			</div>
			<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div> --%>
</body>
</html>