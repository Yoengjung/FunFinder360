<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<title>로그인</title>
<style>
html, body {
	height: 100%;
}

.container1 {
	width: 100%;
	height: 100%;
	min-height: calc(100% - 100px);
	padding-top: 100px;
	position: relative;
}

.login-form-container {
	width: 400px;
	height: 200px;
	position: absolute;
	left: 50%;
	top: 270px;
	transform: translateX(-50%);
	text-align: center;
}

.select-container {
	width: 400px;
	height: 200px;
	display: flex;
}

.select-container .select-item1 {
	width: 190px;
	height: 100%;
	border-radius: 10px;
	border: 1px solid black;
	cursor: pointer;
	background-color: #E0E0E0;
}

.select-container .select-item2 {
	position: absolute;
	right: 0;
	width: 190px;
	height: 100%;
	border: 1px solid black;
	border-radius: 10px;
	cursor: pointer;
	background-color: #E0E0E0;
}

.select-container .select-item1 span, .select-container .select-item2 span
	{
	display: block;
	position: relative;
	top: 30%;
	transform: translateY(-35%);
	font-size: 20px;
}

.select-container .select-item1:hover {
	background-color: #C0C0C0;
}

.select-container .select-item2:hover {
	background-color: #C0C0C0;
}

.select-container {
	margin-top: 20px;
}

.select-login-a {
	display: block;
	position: relative;
	height: 100%;
}

.footer {
	transform: translateY(-150px);
}
</style>
</head>
<body>
	<div class="container1">
		<div class="login-form-container">
			<h2>회원가입</h2>
			<div class="select-container">
				<div class="select-container">
					<div class="select-item1">
						<a href="<%=notWithFormTag%>PersonalJoin" class="select-login-a">
							<span class="material-symbols-outlined icon-tag" style="font-size: 80px;"> person</span>
							<span>개인 회원가입</span>
						</a>
					</div>
					<div class="select-item2">
						<a href="<%=notWithFormTag%>OwnerJoin" class="select-login-a">
							<span class="material-symbols-outlined" style="font-size: 80px;"> apartment </span>
							<span>업주 회원가입</span>
						</a>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%@ include file="../common/footer.jsp"%>