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
	background-color: red;
}

.select-container .select-item1 {
	width: 190px;
	height: 100%;
	background-color: blue;
	border-radius: 10px;
}

.select-container .select-item2 {
	position: absolute; 
	right: 0;
	width: 190px;
	height: 100%;
	background-color: blue;
	border-radius: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="login-form-container">
			<h2>로그인</h2>
			<form action="FunFinder360" method="post">
				<div class="select-container">
					<div class="select-item1">
						<span>개인 로그인</span>
					</div>
					<div class="select-item2">
						<span>업주 로그인</span>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>