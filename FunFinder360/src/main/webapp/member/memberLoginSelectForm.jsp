<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
.container {
	position: absolute;
	top: 130px;
	width: 100%;
	height: 100%;
	max-width: none;
}

.login-form-container {
	width: 400px;
	height: 200px;
	position: absolute;
	left: 50%;
	top: 130px;
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
	top: 50%;
	transform: translateY(-50%);
	font-size: 20px;
	top: 50%;
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
</style>
</head>
<body>
	<div class="container">
		<div class="login-form-container">
			<h2>로그인</h2>
			<div class="select-container">
				<div class="select-container">
					<div class="select-item1">
						<a href="<%=notWithFormTag%>PersonalLogin" class="select-login-a">
							<span>개인 로그인</span>
						</a>
					</div>
					<div class="select-item2">
						<a href="<%=notWithFormTag%>OwnerLogin" class="select-login-a">
							<span>업주 로그인</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>