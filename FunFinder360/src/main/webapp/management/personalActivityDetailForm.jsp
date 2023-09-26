<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/questionCSS/questionDetailFormCSS.css" type="text/css">
<title>회원 활동 상세 보기</title>
</head>
<script>
	function backPage() {
		location.href = "<%=notWithFormTag%>PersonalActivity"
	}
</script>
<body>

	<div class="container">
		<h2>회원 활동 상세 보기</h2>
		<c:if test='${requestScope.activityData[0].ranking == 1}'>
			<c:set var="index" value="0"></c:set>
			<div class="container-1">
				<div class="title-box">
					<p>${requestScope.activityData[index].activityName}</p>
				</div>
				<div class="post-info-box">
					<ul>
						<li class="post-span-column">
							<span>등록자명</span>
						</li>
						<li class="post-span-user post-span">
							<span>관리자</span>
						</li>
						<li class="post-span-column">
							<span>등록일</span>
						</li>
						<li class="post-span-postedDate post-span">
							<span>${requestScope.activityData[index].postedDate}</span>
						</li>
						<li class="post-span-column">
							<span>조회수</span>
						</li>
						<li class="post-span-readhit post-span">
							<span>${requestScope.activityData[index].readHit}</span>
						</li>
					</ul>
				</div>
			</div>
			<button class="back-btn" value="돌아가기" onclick="backPage();">목록</button>

			<div class="page-control-container">
				<ul>
					<li>
						<span>다음글</span>
						<a href="<%=notWithFormTag%>personalActivityDetail&activityId=${requestScope.activityData[index + 2].activityId}">${requestScope.activityData[index + 2].activityName}</a>
					</li>
				</ul>
			</div>
		</c:if>

		<c:if test="${requestScope.activityData[0].ranking == requestScope.totalRecodeCount}">
			<c:set var="index" value="0"></c:set>
			<div class="container-1">
				<div class="title-box">
					<p>${requestScope.activityData[index].activityName}</p>
				</div>
				<div class="post-info-box">
					<ul>
						<li class="post-span-column">
							<span>등록자명</span>
						</li>
						<li class="post-span-user post-span">
							<span>관리자</span>
						</li>
						<li class="post-span-column">
							<span>등록일</span>
						</li>
						<li class="post-span-postedDate post-span">
							<span>${requestScope.activityData[index].postedDate}</span>
						</li>
						<li class="post-span-column">
							<span>조회수</span>
						</li>
						<li class="post-span-readhit post-span">
							<span>${requestScope.activityData[index].readHit}</span>
						</li>
					</ul>
				</div>
			</div>
			<button class="back-btn" value="돌아가기" onclick="backPage();">목록</button>

			<div class="page-control-container">
				<ul>
					<li>
						<span>이전글</span>
						<a href="<%=notWithFormTag%>personalActivityDetail&activityId=${requestScope.activityData[index + 1].activityId}">${requestScope.activityData[index + 1].activityName}</a>
					</li>
				</ul>
			</div>
		</c:if>

		<c:if test="${requestScope.activityData[0].ranking != 1 && requestScope.activityData[0].ranking != requestScope.totalRecodeCount}">
			<c:set var="index" value="0"></c:set>
			<div class="container-1">
				<div class="title-box">
					<p>${requestScope.activityData[index].activityName}</p>
				</div>
				<div class="post-info-box">
					<ul>
						<li class="post-span-column">
							<span>등록자명</span>
						</li>
						<li class="post-span-user post-span">
							<span>관리자</span>
						</li>
						<li class="post-span-column">
							<span>등록일</span>
						</li>
						<li class="post-span-postedDate post-span">
							<span>${requestScope.activityData[index].postedDate}</span>
						</li>
						<li class="post-span-column">
							<span>조회수</span>
						</li>
						<li class="post-span-readhit post-span">
							<span>${requestScope.activityData[index].readHit}</span>
						</li>
					</ul>
				</div>
			</div>
			<div class="list-btn-box">
				<button class="back-btn" value="돌아가기" onclick="backPage();">목록</button>
			</div>
			<div class="page-control-container">
				<ul>
					<li>
						<span>이전글</span>
						<a href="<%=notWithFormTag%>personalActivityDetail&activityId=${requestScope.activityData[index + 1].activityId}">${requestScope.activityData[index + 1].activityName}</a>
					</li>
					<li>
						<span>다음글</span>
						<a href="<%=notWithFormTag%>personalActivityDetail&activityId=${requestScope.activityData[index + 2].activityId}">${requestScope.activityData[index + 2].activityName}</a>
					</li>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>