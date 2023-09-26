<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function backPage() {
		location.href = "<%=notWithFormTag%>commonQuestionsList"
	}
</script>
<style>
.container {
	margin-top: 130px;
	width: 100%;
	height: 100%;
}

.container h2 {
	text-align: center;
}

.container-1 {
	margin-top: 20px;
	width: 100%;
	min-height: 500px;
	border-bottom: 1px solid black;
}

.title-box {
	width: 100%;
	font-size: 25px;
	padding: 10px 5px;
	justify-content: center;
	border-top: 1px solid black;
	font-weight: 600;
}

.title-box p {
	margin: 0px;
}

.post-info-box {
	width: 100%;
	height: 32px;
	border-top: 1px solid #999999;
	border-bottom: 1px solid #999999;
}

.post-info-box span {
	display: block;
	line-height: 30px;
}

.post-info-box ul {
	display: flex;
	flex-direction: row;
	vertical-align: middle;
}

.post-info-box ul .post-span-column {
	width: 15%;
	height: 30px;
	text-align: center;
	justify-content: center;
	background-color: #CCCCCC;
}

.post-span-user {
	width: 15%;
}

.post-span-postedDate {
	width: 25%;
}

.post-span-readhit {
	width: 15%%;
}

.post-span {
	padding-left: 10px;
}

.content-box {
	margin-top: 20px;
	padding: 10px;
	line-height: 40px;
	border-bottom: 1px soild #CCCCFF;
}

.page-control-container ul {
	margin-top: 20px;
	display: flex;
	flex-direction: column;
}

.page-control-container ul li {
	width: 100%;
	height: 30px;
	display: flex;
	flex-direction: row;
	margin-bottom: 5px;
	border: 1px solid #CCCCCC;
}

.page-control-container ul li span {
	position: relative;
	display: block;
	height: 100%;
	width: 90px;
	padding: 0 20px;
	left: 0px;
	text-align: center;
	background-color: #E1E1E1;
	border-right: 1px solid #CCCCCC;
}

.page-control-container ul li a {
	padding-left: 20px;
}

.back-btn {
	margin-top: 20px;
}
</style>
<body>

	<div class="container">
		<h2>자주 묻는 질문</h2>
		<c:if test='${requestScope.commonQuestionData[0].ranking == 1}'>
			<c:set var="index" value="0"></c:set>
			<div class="container-1">
				<div class="title-box">
					<p>${requestScope.commonQuestionData[index].title}</p>
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
							<span>${requestScope.commonQuestionData[index].postedDate}</span>
						</li>
						<li class="post-span-column">
							<span>조회수</span>
						</li>
						<li class="post-span-readhit post-span">
							<span>${requestScope.commonQuestionData[index].readhit}</span>
						</li>
					</ul>
				</div>
				<div class="content-box">
					<p>${requestScope.commonQuestionData[index].content}</p>
				</div>
			</div>
			<button class="back-btn" value="돌아가기" onclick="backPage();">목록</button>
				<c:set var="member" value="${sessionScope.loginfo}" />
				<c:if test="${not empty member and member.userId == 'admin1'}">
					<a id="deleteAnchor" href="<%=notWithFormTag%>commonQuestionsDelete&questionId=${requestScope.commonQuestionData[index].questionId}">${requestScope.commonQuestionData[index].title}">
						삭제
				    </a>
			    </c:if>

			<div class="page-control-container">
				<ul>
					<li>
						<span>다음글</span>
						<a href="<%=notWithFormTag%>commonQuestionsDetail&questionId=${requestScope.commonQuestionData[index + 1].questionId}">${requestScope.commonQuestionData[index + 1].title}</a>
					</li>
				</ul>
			</div>
		</c:if>

		<c:if test="${requestScope.commonQuestionData[0].ranking == requestScope.totalRecodeCount}">
			<c:set var="index" value="0"></c:set>
			<div class="container-1">
				<div class="title-box">
					<p>${requestScope.commonQuestionData[index].title}</p>
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
							<span>${requestScope.commonQuestionData[index].postedDate}</span>
						</li>
						<li class="post-span-column">
							<span>조회수</span>
						</li>
						<li class="post-span-readhit post-span">
							<span>${requestScope.commonQuestionData[index].readhit}</span>
						</li>
					</ul>
				</div>
				<div class="content-box">
					<p>${requestScope.commonQuestionData[index].content}</p>
				</div>
			</div>
			<button class="back-btn" value="돌아가기" onclick="backPage();">목록</button>
				<c:set var="member" value="${sessionScope.loginfo}" />
				<c:if test="${not empty member and member.userId == 'admin1'}">
					<a id="deleteAnchor" href="<%=notWithFormTag%>commonQuestionsDelete&questionId=${requestScope.commonQuestionData[index].questionId}">${requestScope.commonQuestionData[index].title}">
						삭제
				    </a>
			    </c:if>

			<div class="page-control-container">
				<ul>
					<li>
						<span>이전글</span>
						<a href="<%=notWithFormTag%>commonQuestionsDetail&questionId=${requestScope.commonQuestionData[index + 1].questionId}">${requestScope.commonQuestionData[index + 1].title}</a>
					</li>
				</ul>
			</div>
		</c:if>

		<c:if test="${requestScope.commonQuestionData[0].ranking != 1 && requestScope.commonQuestionData[0].ranking != requestScope.totalRecodeCount}">
			<c:set var="index" value="0"></c:set>
			<div class="container-1">
				<div class="title-box">
					<p>${requestScope.commonQuestionData[index].title}</p>
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
							<span>${requestScope.commonQuestionData[index].postedDate}</span>
						</li>
						<li class="post-span-column">
							<span>조회수</span>
						</li>
						<li class="post-span-readhit post-span">
							<span>${requestScope.commonQuestionData[index].readhit}</span>
						</li>
					</ul>
				</div>
				<div class="content-box">
					<p>${requestScope.commonQuestionData[index].content}</p>
				</div>
			</div>
			<button class="back-btn" value="돌아가기" onclick="backPage();">목록</button>
				<c:set var="member" value="${sessionScope.loginfo}" />
				<c:if test="${not empty member and member.userId == 'admin1'}">
					<a id="deleteAnchor" href="<%=notWithFormTag%>commonQuestionsDelete&questionId=${requestScope.commonQuestionData[index].questionId}">${requestScope.commonQuestionData[index].title}">
						삭제
				    </a>
			    </c:if>

			<div class="page-control-container">
				<ul>
					<li>
						<span>이전글</span>
						<a href="<%=notWithFormTag%>commonQuestionsDetail&questionId=${requestScope.commonQuestionData[index + 1].questionId}">${requestScope.commonQuestionData[index + 1].title}</a>
					</li>
					<li>
						<span>다음글</span>
						<a href="<%=notWithFormTag%>commonQuestionsDetail&questionId=${requestScope.commonQuestionData[index + 2].questionId}">${requestScope.commonQuestionData[index + 2].title}</a>
					</li>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>