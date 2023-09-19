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
.table {
	margin-top: 50px;
}
#td-box {
	background-color: #CCCCCC;
}
</style>
</head>
<body>
	<div class="container">
		<h2>${requestScope.bean.userName} 회원 정보</h2>
		<table class="table">
			<thead></thead>
			<tbody>
				<tr>
					<td align="center" id="td-box" width="200px">아이디</td>
					<td>${requestScope.bean.userId}</td>
				</tr>
				<tr>
					<td align="center"  id="td-box">비밀 번호:</td>
					<td>${requestScope.bean.password}</td>
				</tr>
				<tr>
					<td align="center" id="td-box">이름:</td>
					<td>${requestScope.bean.userName}</td>
				</tr>
				<tr>
					<td align="center" id="td-box">사업 이름:</td>
					<td>${requestScope.bean.businessName}</td>
				</tr>
				<tr>
					<td align="center" id="td-box">사업 구분:</td>
					<td>${requestScope.bean.businessType}</td>
				</tr>
				<tr>
					<td align="center" id="td-box">사업자 등록번호:</td>
					<td>${requestScope.bean.businessNumber}</td>
				</tr>
				<tr>
					<td align="center" id="td-box">전화번호:</td>
					<td>${requestScope.bean.phoneNumber}</td>
				</tr>
				<tr>
					<td align="center" id="td-box">이메일:</td>
					<td>${requestScope.bean.email}</td>
				</tr>
				<tr>
					<td align="center" id="td-box">자기 소개:</td>
					<td>${requestScope.bean.bio}</td>
				</tr>
				<tr>
					<td align="center" id="td-box">가입 일자:</td>
					<td>${bean.registrationDate}</td>
				</tr>
			</tbody>
		</table>
		<div id="backButton">
			<button type="button" class="btn btn-primary" onclick="history.back();">돌아 가기</button>
		</div>
	</div>
</body>
</html>