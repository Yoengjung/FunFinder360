<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/questionCSS/questionDetailFormCSS.css"
	type="text/css">
<title>개인 질문 상세 보기</title>
</head>
<script>
function validation () {
	var respond = $("#respond").val()
	
	if (rating == 0) {
		$("#respond").css("display", "block");
		return false;
	}
}


	function backPage() {
		location.href = "<%=notWithFormTag%>questionsList"
	}

	$(document).ready(function() {
		var isButtonVisible = true; // 초기에 버튼이 보이도록 설정

		$("#response-id-btn").click(function() {
			if (isButtonVisible) {
				$("#success-btn").css("display", "block"); // 완료 버튼 표시
				isButtonVisible = false; // 가시성 상태를 토글
				$("#response-id-btn").css("display", "none"); // 답글 버튼
			}

			$(".respond").css("display", "block"); // 입력란 표시
			$(".respond").focus();
		});

		$("#success-btn").click(function() {
			$("#response-id-btn").css("display", "block");
			$("#success-btn").css("display", "none");
			isButtonVisible = true;
		});
	});
</script>

<style>
#success-btn {
	display: none;
}

.respond {
	display: none;
}

/* 입력란 스타일 */
.respond {
	padding: 10px; /* 내용과 테두리 사이의 간격 */
	border: 1px solid #ccc; /* 회색 테두리 */
	border-radius: 5px; /* 둥근 테두리 */
	box-sizing: border-box; /* 테두리를 포함한 전체 너비를 사용 */
	margin-right: 10px; /* 오른쪽 여백 추가 */
	width: calc(100% - 110px); /* 버튼 너비를 제외한 나머지 너비 계산 */
}

/* 버튼 스타일 */
.adminbtn {
	padding: 10px 20px; /* 버튼 내부 여백 */
	border: none; /* 테두리 없음 */
	border-radius: 5px; /* 둥근 테두리 */
	cursor: pointer; /* 포인터 커서 표시 */
	color: #fff; /* 글자색 */
	width: 100px; /* 버튼 너비 */
}

/* 주요 버튼 스타일 */
.btn-primary {
	background-color: #007bff; /* 파란색 배경색 */
}

/* 어두운 버튼 스타일 */
.btn-dark {
	background-color: #343a40; /* 어두운 회색 배경색 */
}

textarea {
	resize: none;
}
.respond {
	width: 100%;
	display: block;
}
.adminbtn {
	position: relative;
	left: 0px;
}
</style>
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
						<li class="post-span-column"><span>등록자명</span></li>
						<li class="post-span-user post-span"><span>관리자</span></li>
						<li class="post-span-column"><span>등록일</span></li>
						<li class="post-span-postedDate post-span"><span>${requestScope.questionData[index].postedDate}</span>
						</li>
						<li class="post-span-column"><span>조회수</span></li>
						<li class="post-span-readhit post-span"><span>${requestScope.questionData[index].readhit}</span>
						</li>
					</ul>
				</div>
				<div class="content-box">
					<p>${requestScope.questionData[index].content}</p>
				</div>
			</div>
			<div class="list-btn-box">
				<button class="btn btn-secondary back-btn" value="돌아가기"
					onclick="backPage();">목록</button>
			</div>
			<div class="page-control-container">
				<ul>
					<li><span>다음글</span> <a
						href="<%=notWithFormTag%>questionsDetail&questionListId=${requestScope.questionData[index + 1].questionListId}">${requestScope.questionData[index + 1].title}</a>
					</li>

					<c:set var="member" value="${sessionScope.loginfo}" />
					<c:if test="${not empty member and member.userId == 'admin1'}">
						<div class="form-container">
							<form action="<%=withFormTag%>" method="post">
								<input type="hidden" id="command" name="command" value="respond">
								<input type="hidden" id="questionListId" name="questionListId"
									value="${requestScope.questionData[0].questionListId}">
								<div>
									<textarea class="respond" id="respond" name="respond"></textarea>
									<button type="button" class="adminbtn btn-dark"
										id="response-id-btn">답글</button>
								</div>

								<button type="submit" class="adminbtn btn-primary"
									id="success-btn" onclick="return validation();">완료</button>
							</form>
						</div>
					</c:if>
				</ul>
			</div>
		</c:if>

		<c:if
			test="${requestScope.questionData[0].ranking == requestScope.totalRecodeCount}">
			<c:set var="index" value="0"></c:set>
			<div class="container-1">
				<div class="title-box">
					<p>${requestScope.questionData[index].title}</p>
				</div>
				<div class="post-info-box">
					<ul>
						<li class="post-span-column"><span>등록자명</span></li>
						<li class="post-span-user post-span"><span>관리자</span></li>
						<li class="post-span-column"><span>등록일</span></li>
						<li class="post-span-postedDate post-span"><span>${requestScope.questionData[index].postedDate}</span>
						</li>
						<li class="post-span-column"><span>조회수</span></li>
						<li class="post-span-readhit post-span"><span>${requestScope.questionData[index].readhit}</span>
						</li>
					</ul>
				</div>
				<div class="content-box">
					<p>${requestScope.questionData[index].content}</p>
				</div>
			</div>
			<div class="list-btn-box">
				<button class="btn btn-secondary back-btn" value="돌아가기"
					onclick="backPage();">목록</button>
			</div>

			<c:set var="member" value="${sessionScope.loginfo}" />
			<c:if test="${not empty member and member.userId == 'admin1'}">

			</c:if>

			<div class="page-control-container">
				<ul>
					<li><span>이전글</span> <a
						href="<%=notWithFormTag%>questionsDetail&questionListId=${requestScope.questionData[index + 1].questionListId}">${requestScope.questionData[index + 1].title}</a>
					</li>
					<c:set var="member" value="${sessionScope.loginfo}" />
					<c:if test="${not empty member and member.userId == 'admin1'}">
						<div class="form-container">
							<form action="<%=withFormTag%>" method="post">
								<input type="hidden" id="command" name="command" value="respond">
								<input type="hidden" id="questionListId" name="questionListId"
									value="${requestScope.questionData[0].questionListId}">
								<div>
									<textarea class="respond" id="respond" name="respond"></textarea>
									<button type="button" class="adminbtn btn-dark"
										id="response-id-btn">답글</button>
								</div>

								<button type="submit" class="adminbtn btn-primary"
									id="success-btn" onclick="return validation();">완료</button>
							</form>
						</div>
					</c:if>
				</ul>
			</div>
		</c:if>

		<c:if
			test="${requestScope.questionData[0].ranking != 1 && requestScope.questionData[0].ranking != requestScope.totalRecodeCount}">
			<c:set var="index" value="0"></c:set>
			<div class="container-1">
				<div class="title-box">
					<p>${requestScope.questionData[index].title}</p>
				</div>
				<div class="post-info-box">
					<ul>
						<li class="post-span-column"><span>등록자명</span></li>
						<li class="post-span-user post-span"><span>관리자</span></li>
						<li class="post-span-column"><span>등록일</span></li>
						<li class="post-span-postedDate post-span"><span>${requestScope.questionData[index].postedDate}</span>
						</li>
						<li class="post-span-column"><span>조회수</span></li>
						<li class="post-span-readhit post-span"><span>${requestScope.questionData[index].readhit}</span>
						</li>
					</ul>
				</div>
				<div class="content-box">
					<p>${requestScope.questionData[index].content}</p>
				</div>
			</div>
			<div class="list-btn-box">
				<button class="btn btn-secondary back-btn" value="돌아가기"
					onclick="backPage();">목록</button>
			</div>

			<c:set var="member" value="${sessionScope.loginfo}" />
			<c:if test="${not empty member and member.userId == 'admin1'}">
				<button>답글</button>
			</c:if>

			<c:set var="member" value="${sessionScope.loginfo}" />
			<c:if test="${not empty member and member.userId == 'admin1'}">

			</c:if>
			<div class="page-control-container">
				<ul>
					<li><span>이전글</span> <a
						href="<%=notWithFormTag%>questionsDetail&questionListId=${requestScope.questionData[index + 1].questionListId}">${requestScope.questionData[index + 1].title}</a>
					</li>
					<li><span>다음글</span> <a
						href="<%=notWithFormTag%>questionsDetail&questionListId=${requestScope.questionData[index + 2].questionListId}">${requestScope.questionData[index + 2].title}</a>
					</li>
					<c:set var="member" value="${sessionScope.loginfo}" />
					<c:if test="${not empty member and member.userId == 'admin1'}">
						<div class="form-container">
							<form action="<%=withFormTag%>" method="post">
								<input type="hidden" id="command" name="command" value="respond">
								<input type="hidden" id="questionListId" name="questionListId"
									value="${requestScope.questionData[0].questionListId}">
								<div>
									<textarea class="respond" id="respond" name="respond"></textarea>
									<button type="button" class="adminbtn btn-dark"
										id="response-id-btn">답글</button>
								</div>

								<button type="submit" class="adminbtn btn-primary"
									id="success-btn" onclick="return validation();">완료</button>
							</form>
						</div>
					</c:if>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>