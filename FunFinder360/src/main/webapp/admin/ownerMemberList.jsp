<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업주 회원 정보 관리</title>
<style>
.container {
	margin-top: 9%;
	width: 100%;
	height: 100%;
	max-width: 1460px;
}

.container h2 {
	text-align: center;
	margin-bottom: 40px;
}

#pagingStatus-span {
	position: relative;
	display: block;
	float: right;
	font-size: 20px;
	margin-bottom: 10px;
	border: 2px solid black;
	border-radius: 10px;
	width: 132px;
	padding: 0px 10px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>업주 회원 리스트</h2>
		<span id="pagingStatus-span">${requestScope.pageInfo.pagingStatus}</span>
		<table class="table talbe-hover">
			<thead class="table-dark">
				<tr>
					<th>아이디</th>
					<th>비밀 번호</th>
					<th>이름</th>
					<th>사업명</th>
					<th>사업구분</th>
					<th>사업등록번호</th>
					<th>전화번호</th>
					<th>이메일</th>
					<th>가입일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.datalist}">
					<tr>
						<td>${bean.userId}</td>
						<td>${bean.password}</td>
						<td>${bean.userName}</td>
						<td>${bean.businessName}</td>
						<td>${bean.businessType}</td>
						<td>${bean.businessNumber}</td>
						<td>${bean.phoneNumber}</td>
						<td>${bean.email}</td>
						<td>${bean.registrationDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="paging-container">${requestScope.pageInfo.pagingHtml}</div>
</body>
</html>