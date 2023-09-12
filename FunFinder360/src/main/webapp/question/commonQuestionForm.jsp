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
</style>
</head>
<body>
	<div class="container">
		<h2>자주 묻는 질문</h2>
		<table class="table">
			<thead class="table-success">
				<tr>
					<th class="table-head-box no-head">순번</th>
					<th class="table-head-box title-head">제목</th>
					<th class="table-head-box registrant-head">등록자명</th>
					<th class="table-head-box posted-date-head">등록일자</th>
					<th class="table-head-box readhit-head">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="qeustion" items="${requestScope.commonQuestionData}">
					<tr>
						<td class="table-body-box">${qeustion.question_id}</td>
						<td class="table-body-box title-box">
							<a href="<%=notWithFormTag%>commonQuestionsDetail&qeustion_id=${qeustion.question_id}">${qeustion.title}</a>
						</td>
						<td class="table-body-box">${qeustion.userId}</td>
						<td class="table-body-box">${qeustion.postedDate}</td>
						<td class="table-body-box">${qeustion.readhit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	${requestScope.pageInfo.pagingHtml}
</body>
</html>