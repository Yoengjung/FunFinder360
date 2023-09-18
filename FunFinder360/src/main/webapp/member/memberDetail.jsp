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
</style>
</head>
<body>
	<div class="container">
		<h2>${requestScope.bean.username}회원리스트</h2>
		<table class="table">
			<thead></thead>
			<tbody>
				<tr>
					<td align="left">아이디</td>
					<td>${requestScope.bean.userId}</td>
				</tr>
				<tr>
					<td align="left">비밀 번호:</td>
					<td>${requestScope.bean.password}</td>
				</tr>
				<tr>
					<td align="left">이름:</td>
					<td>${requestScope.bean.username}</td>
				</tr>
				<tr>
					<td align="left">생년월일:</td>
					<td>${requestScope.bean.birth}</td>
				</tr>
				<tr>
					<td align="left">전화번호:</td>
					<td>${requestScope.bean.phoneNumber}</td>
				</tr>
				<tr>
					<td align="left">이메일:</td>
					<td>${requestScope.bean.email}</td>
				</tr>
				<tr>
					<td align="left">자기 소개:</td>
					<td>${requestScope.bean.bio}</td>
				</tr>

				<tr>
					<td align="left">가입 일자:</td>
					<td>${bean.registration_date}</td>
				</tr>
			</tbody>
		</table>
		<div id="backButton">
			<button type="button" class="btn btn-primary" onclick="history.back();">돌아 가기</button>
		</div>
	</div>
</body>
</html>