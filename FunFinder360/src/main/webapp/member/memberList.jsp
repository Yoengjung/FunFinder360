<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>

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
	background-color: blue;
}
</style>
</head>
<body>

	<div class="container">
		<h2>회원 리스트</h2>
		<p>회원 리스트를 보여주는 페이지 입니다.</p>
		<table class="table talbe-hover">
			<thead class="table-dark">
				<tr>
					<th>아이디</th>
					<th>비밀 번호</th>
					<th>이름</th>
					<th>생년월일</th>
					<th>전화번호</th>
					<th>이메일</th>
					<th>가입 일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.datalist}">
					<tr>
						<td>${bean.userId}</td>
						<td>${bean.password}</td>
						<td>${bean.username}</td>
						<td>${bean.birth}</td>
						<td>${bean.phoneNumber}</td>
						<td>${bean.email}</td>
						<td>${bean.registration_date}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>