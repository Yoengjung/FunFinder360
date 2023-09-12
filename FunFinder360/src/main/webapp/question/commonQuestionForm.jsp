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
	background-color: red;
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
				<tr>
					<td class="table-body-box">1</td>
					<td class="table-body-box">Doe</td>
					<td class="table-body-box">관리자</td>
					<td class="table-body-box">2020-10-20</td>
					<td class="table-body-box">2</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>