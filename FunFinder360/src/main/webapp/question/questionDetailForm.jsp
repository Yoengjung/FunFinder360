<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/questionCSS/questionDetailFormCSS.css" type="text/css">
<title>개인 질문 상세 보기</title>
</head>
<script>
	function backPage() {
		location.href = "<%=notWithFormTag%>questionsList"
	}
</script>
<body>

	<div class="container">
		<h2>자주 묻는 질문</h2>
		<c:if test='${requestScope.questionData[0].ranking == 1}'>
			<c:set var="index" value="0"></c:set>
			<div class="container-1">
				<div class="title-box">
					<p>${requestScope.questionData[index].title}</p>
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
							<span>${requestScope.questionData[index].postedDate}</span>
						</li>
						<li class="post-span-column">
							<span>조회수</span>
						</li>
						<li class="post-span-readhit post-span">
							<span>${requestScope.questionData[index].readhit}</span>
						</li>
					</ul>
				</div>
				<div class="content-box">
					<p>${requestScope.questionData[index].content}</p>
				</div>
			</div>
			<div class="list-btn-box">
				<button class="btn btn-secondary back-btn" value="돌아가기" onclick="backPage();">목록</button>
			</div>
			<div class="page-control-container">
				<ul>
					<li>
						<span>다음글</span>
						<a href="<%=notWithFormTag%>questionsDetail&questionListId=${requestScope.questionData[index + 1].questionListId}">${requestScope.questionData[index + 1].title}</a>
					</li>
				</ul>
			</div>
		</c:if>

		<c:if test="${requestScope.questionData[0].ranking == requestScope.totalRecodeCount}">
			<c:set var="index" value="0"></c:set>
			<div class="container-1">
				<div class="title-box">
					<p>${requestScope.questionData[index].title}</p>
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
							<span>${requestScope.questionData[index].postedDate}</span>
						</li>
						<li class="post-span-column">
							<span>조회수</span>
						</li>
						<li class="post-span-readhit post-span">
							<span>${requestScope.questionData[index].readhit}</span>
						</li>
					</ul>
				</div>
				<div class="content-box">
					<p>${requestScope.questionData[index].content}</p>
				</div>
			</div>
			<div class="list-btn-box">
				<button class="btn btn-secondary back-btn" value="돌아가기" onclick="backPage();">목록</button>
			</div>
			<div class="page-control-container">
				<ul>
					<li>
						<span>이전글</span>
						<a href="<%=notWithFormTag%>questionsDetail&questionListId=${requestScope.questionData[index + 1].questionListId}">${requestScope.questionData[index + 1].title}</a>
					</li>
				</ul>
			</div>
		</c:if>

		<c:if test="${requestScope.questionData[0].ranking != 1 && requestScope.questionData[0].ranking != requestScope.totalRecodeCount}">
			<c:set var="index" value="0"></c:set>
			<div class="container-1">
				<div class="title-box">
					<p>${requestScope.questionData[index].title}</p>
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
							<span>${requestScope.questionData[index].postedDate}</span>
						</li>
						<li class="post-span-column">
							<span>조회수</span>
						</li>
						<li class="post-span-readhit post-span">
							<span>${requestScope.questionData[index].readhit}</span>
						</li>
					</ul>
				</div>
				<div class="content-box">
					<p>${requestScope.questionData[index].content}</p>
				</div>
			</div>
			<div class="list-btn-box">
				<button class="btn btn-secondary back-btn" value="돌아가기" onclick="backPage();">목록</button>
			</div>
			<div class="page-control-container">
				<ul>
					<li>
						<span>이전글</span>
						<a href="<%=notWithFormTag%>questionsDetail&questionListId=${requestScope.questionData[index + 1].questionListId}">${requestScope.questionData[index + 1].title}</a>
					</li>
					<li>
						<span>다음글</span>
						<a href="<%=notWithFormTag%>questionsDetail&questionListId=${requestScope.questionData[index + 2].questionListId}">${requestScope.questionData[index + 2].title}</a>
					</li>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>