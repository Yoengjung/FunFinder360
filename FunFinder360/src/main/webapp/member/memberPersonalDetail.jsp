<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberCSS/memberPersonalDetailCSS.css" type="text/css">
</head>
<body>
	<div class="container">
		<h2>${requestScope.bean.username}회원리스트</h2>
		<table class="table">
			<thead></thead>
			<tbody>
				<tr>
					<td align="center" class="td-tag" style="background-color: #CCCCCC;">아이디</td>
					<td class="content-tag" style="padding-left: 20px;">${requestScope.bean.userId}</td>
				</tr>
				<tr>
					<td align="center" class="td-tag" style="background-color: #CCCCCC;">비밀 번호</td>
					<td class="content-tag" style="padding-left: 20px;">${requestScope.bean.password}</td>
				</tr>
				<tr>
					<td align="center" class="td-tag" style="background-color: #CCCCCC;">이름</td>
					<td class="content-tag" style="padding-left: 20px;">${requestScope.bean.username}</td>
				</tr>
				<tr>
					<td align="center" class="td-tag" style="background-color: #CCCCCC;">생년월일</td>
					<td class="content-tag" style="padding-left: 20px;">${requestScope.bean.birth}</td>
				</tr>
				<tr>
					<td align="center" class="td-tag" style="background-color: #CCCCCC;">전화번호</td>
					<td class="content-tag" style="padding-left: 20px;">${requestScope.bean.phoneNumber}</td>
				</tr>
				<tr>
					<td align="center" class="td-tag" style="background-color: #CCCCCC;">이메일</td>
					<td class="content-tag" style="padding-left: 20px;">${requestScope.bean.email}</td>
				</tr>
				<tr>
					<td align="center" class="td-tag" style="background-color: #CCCCCC;">자기 소개</td>
					<td class="content-tag" style="padding-left: 20px;">${requestScope.bean.bio}</td>
				</tr>

				<tr class="bottom-tr">
					<td align="center" class="td-tag" style="background-color: #CCCCCC;">가입 일자</td>
					<td class="content-tag" style="padding-left: 20px;">${bean.registration_date}</td>
				</tr>
			</tbody>
		</table>
		<div id="backButton">
			<button type="button" class="btn btn-primary" onclick="history.back();">돌아 가기</button>
		</div>
	</div>
</body>
</html>