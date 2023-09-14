<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.container {
	margin-top: 130px;
	width: 100%;
	height: 100%;
}

.container h2 {
	margin-bottom: 30px;
}

.table-head-box {
	text-align: center;
}

.table-body-box {
	text-align: center;
}

.no-head {
	width: 10%;
}

.title-head {
	text-align: left;
	width: 50%;
}

.registrant-head {
	width: 10%;
}

.posted-date-head {
	width: 20%;
}

.readhit-head {
	width: 10%;
}

.title-box {
	text-align: left;
}

.title-box a {
	color: #3366FF;
}

.title-box a:hover {
	text-decoration: underline;
}

.input-group {
	width: 300px;
}

.form-select {
	width: 200px;
}

.search-form {
	display: flex;
	flex-direction: row;
	justify-content: center;
}

.keyword-input-box {
	margin: 0 20px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>자주 묻는 질문</h2>
		<table class="table">
			<thead class="table-dark">
				<tr>
					<th class="table-head-box no-head">순번</th>
					<th class="table-head-box title-head">제목</th>
					<th class="table-head-box registrant-head">등록자명</th>
					<th class="table-head-box posted-date-head">등록일자</th>
					<th class="table-head-box readhit-head">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="question" items="${requestScope.commonQuestionData}">
					<tr>
						<td class="table-body-box">${question.question_id}</td>
						<td class="table-body-box title-box">
							<a href="<%=notWithFormTag%>commonQuestionsDetail&question_id=${question.question_id}">${question.title}</a>
						</td>
						<td class="table-body-box">${question.userId}</td>
						<td class="table-body-box">${question.postedDate}</td>
						<td class="table-body-box">${question.readhit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="search-container">
			<div class="search-in-container">
				<form name="search-form" action="<%=withFormTag%>" method="get" class="search-form">
					<input type="hidden" name="command" value="prList">
					<select id="mode" name="mode" class="form-select">
						<option value="all" selected="selected">--- 선택해 주세요 ---
						<option value="name">제목
						<option value="company">내용
					</select>
					<div class="input-group">
						<input class="keyword-input-box form-control" type="text" name="keyword" id="keyword" placeholder="키워드 입력" autocomplete="off">
					</div>
					<button type="submit" class="btn btn-default form-control-sm search-btn" onclick="">검색</button>
				</form>
			</div>
		</div>
	</div>
	${requestScope.pageInfo.pagingHtml}
</body>
</html>